import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDeliveryMethod } from 'app/shared/model/delivery-method.model';
import { DeliveryMethodService } from './delivery-method.service';

@Component({
  templateUrl: './delivery-method-delete-dialog.component.html'
})
export class DeliveryMethodDeleteDialogComponent {
  deliveryMethod?: IDeliveryMethod;

  constructor(
    protected deliveryMethodService: DeliveryMethodService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.deliveryMethodService.delete(id).subscribe(() => {
      this.eventManager.broadcast('deliveryMethodListModification');
      this.activeModal.close();
    });
  }
}
