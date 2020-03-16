import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOfferProduct } from 'app/shared/model/offer-product.model';
import { OfferProductService } from './offer-product.service';

@Component({
  templateUrl: './offer-product-delete-dialog.component.html'
})
export class OfferProductDeleteDialogComponent {
  offerProduct?: IOfferProduct;

  constructor(
    protected offerProductService: OfferProductService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.offerProductService.delete(id).subscribe(() => {
      this.eventManager.broadcast('offerProductListModification');
      this.activeModal.close();
    });
  }
}
