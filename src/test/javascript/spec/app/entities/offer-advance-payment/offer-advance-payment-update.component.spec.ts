import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { OfferAdvancePaymentUpdateComponent } from 'app/entities/offer-advance-payment/offer-advance-payment-update.component';
import { OfferAdvancePaymentService } from 'app/entities/offer-advance-payment/offer-advance-payment.service';
import { OfferAdvancePayment } from 'app/shared/model/offer-advance-payment.model';

describe('Component Tests', () => {
  describe('OfferAdvancePayment Management Update Component', () => {
    let comp: OfferAdvancePaymentUpdateComponent;
    let fixture: ComponentFixture<OfferAdvancePaymentUpdateComponent>;
    let service: OfferAdvancePaymentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [OfferAdvancePaymentUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(OfferAdvancePaymentUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OfferAdvancePaymentUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OfferAdvancePaymentService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OfferAdvancePayment('123');
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
        const entity = new OfferAdvancePayment();
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
