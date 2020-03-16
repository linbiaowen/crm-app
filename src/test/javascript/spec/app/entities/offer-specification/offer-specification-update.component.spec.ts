import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { OfferSpecificationUpdateComponent } from 'app/entities/offer-specification/offer-specification-update.component';
import { OfferSpecificationService } from 'app/entities/offer-specification/offer-specification.service';
import { OfferSpecification } from 'app/shared/model/offer-specification.model';

describe('Component Tests', () => {
  describe('OfferSpecification Management Update Component', () => {
    let comp: OfferSpecificationUpdateComponent;
    let fixture: ComponentFixture<OfferSpecificationUpdateComponent>;
    let service: OfferSpecificationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [OfferSpecificationUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(OfferSpecificationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OfferSpecificationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OfferSpecificationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OfferSpecification('123');
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new OfferSpecification();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
