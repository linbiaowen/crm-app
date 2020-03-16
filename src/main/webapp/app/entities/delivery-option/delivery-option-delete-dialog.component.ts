import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDeliveryOption } from 'app/shared/model/delivery-option.model';
import { DeliveryOptionService } from './delivery-option.service';

@Component({
  templateUrl: './delivery-option-delete-dialog.component.html'
})
export class DeliveryOptionDeleteDialogComponent {
  deliveryOption?: IDeliveryOption;

  constructor(
    protected deliveryOptionService: DeliveryOptionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.deliveryOptionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('deliveryOptionListModification');
      this.activeModal.close();
    });
  }
}
