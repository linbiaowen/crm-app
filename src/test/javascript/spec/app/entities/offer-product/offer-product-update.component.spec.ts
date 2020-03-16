import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { OfferProductUpdateComponent } from 'app/entities/offer-product/offer-product-update.component';
import { OfferProductService } from 'app/entities/offer-product/offer-product.service';
import { OfferProduct } from 'app/shared/model/offer-product.model';

describe('Component Tests', () => {
  describe('OfferProduct Management Update Component', () => {
    let comp: OfferProductUpdateComponent;
    let fixture: ComponentFixture<OfferProductUpdateComponent>;
    let service: OfferProductService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [OfferProductUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(OfferProductUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OfferProductUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OfferProductService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OfferProduct('123');
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
        const entity = new OfferProduct();
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
