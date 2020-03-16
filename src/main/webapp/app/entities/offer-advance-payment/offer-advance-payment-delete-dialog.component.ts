import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOfferAdvancePayment } from 'app/shared/model/offer-advance-payment.model';
import { OfferAdvancePaymentService } from './offer-advance-payment.service';

@Component({
  templateUrl: './offer-advance-payment-delete-dialog.component.html'
})
export class OfferAdvancePaymentDeleteDialogComponent {
  offerAdvancePayment?: IOfferAdvancePayment;

  constructor(
    protected offerAdvancePaymentService: OfferAdvancePaymentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.offerAdvancePaymentService.delete(id).subscribe(() => {
      this.eventManager.broadcast('offerAdvancePaymentListModification');
      this.activeModal.close();
    });
  }
}
