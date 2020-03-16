import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { OfferDiscountUpdateComponent } from 'app/entities/offer-discount/offer-discount-update.component';
import { OfferDiscountService } from 'app/entities/offer-discount/offer-discount.service';
import { OfferDiscount } from 'app/shared/model/offer-discount.model';

describe('Component Tests', () => {
  describe('OfferDiscount Management Update Component', () => {
    let comp: OfferDiscountUpdateComponent;
    let fixture: ComponentFixture<OfferDiscountUpdateComponent>;
    let service: OfferDiscountService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [OfferDiscountUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(OfferDiscountUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OfferDiscountUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OfferDiscountService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OfferDiscount('123');
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
        const entity = new OfferDiscount();
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
