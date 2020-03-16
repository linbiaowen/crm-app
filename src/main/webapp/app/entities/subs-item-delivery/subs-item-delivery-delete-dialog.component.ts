import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISubsItemDelivery } from 'app/shared/model/subs-item-delivery.model';
import { SubsItemDeliveryService } from './subs-item-delivery.service';

@Component({
  templateUrl: './subs-item-delivery-delete-dialog.component.html'
})
export class SubsItemDeliveryDeleteDialogComponent {
  subsItemDelivery?: ISubsItemDelivery;

  constructor(
    protected subsItemDeliveryService: SubsItemDeliveryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.subsItemDeliveryService.delete(id).subscribe(() => {
      this.eventManager.broadcast('subsItemDeliveryListModification');
      this.activeModal.close();
    });
  }
}
