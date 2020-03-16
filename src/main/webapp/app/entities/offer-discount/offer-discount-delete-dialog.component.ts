import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOfferDiscount } from 'app/shared/model/offer-discount.model';
import { OfferDiscountService } from './offer-discount.service';

@Component({
  templateUrl: './offer-discount-delete-dialog.component.html'
})
export class OfferDiscountDeleteDialogComponent {
  offerDiscount?: IOfferDiscount;

  constructor(
    protected offerDiscountService: OfferDiscountService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.offerDiscountService.delete(id).subscribe(() => {
      this.eventManager.broadcast('offerDiscountListModification');
      this.activeModal.close();
    });
  }
}
