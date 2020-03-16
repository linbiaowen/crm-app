import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CrmwebTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { OfferAdvancePaymentDeleteDialogComponent } from 'app/entities/offer-advance-payment/offer-advance-payment-delete-dialog.component';
import { OfferAdvancePaymentService } from 'app/entities/offer-advance-payment/offer-advance-payment.service';

describe('Component Tests', () => {
  describe('OfferAdvancePayment Management Delete Component', () => {
    let comp: OfferAdvancePaymentDeleteDialogComponent;
    let fixture: ComponentFixture<OfferAdvancePaymentDeleteDialogComponent>;
    let service: OfferAdvancePaymentService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [OfferAdvancePaymentDeleteDialogComponent]
      })
        .overrideTemplate(OfferAdvancePaymentDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OfferAdvancePaymentDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OfferAdvancePaymentService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete('123');
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith('123');
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
