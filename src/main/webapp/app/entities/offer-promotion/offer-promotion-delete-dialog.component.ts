import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOfferPromotion } from 'app/shared/model/offer-promotion.model';
import { OfferPromotionService } from './offer-promotion.service';

@Component({
  templateUrl: './offer-promotion-delete-dialog.component.html'
})
export class OfferPromotionDeleteDialogComponent {
  offerPromotion?: IOfferPromotion;

  constructor(
    protected offerPromotionService: OfferPromotionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.offerPromotionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('offerPromotionListModification');
      this.activeModal.close();
    });
  }
}
