import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISubscriptionProduct } from 'app/shared/model/subscription-product.model';
import { SubscriptionProductService } from './subscription-product.service';

@Component({
  templateUrl: './subscription-product-delete-dialog.component.html'
})
export class SubscriptionProductDeleteDialogComponent {
  subscriptionProduct?: ISubscriptionProduct;

  constructor(
    protected subscriptionProductService: SubscriptionProductService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.subscriptionProductService.delete(id).subscribe(() => {
      this.eventManager.broadcast('subscriptionProductListModification');
      this.activeModal.close();
    });
  }
}
