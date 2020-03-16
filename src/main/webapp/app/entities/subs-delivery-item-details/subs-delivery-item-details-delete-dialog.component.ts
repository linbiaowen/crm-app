import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISubsDeliveryItemDetails } from 'app/shared/model/subs-delivery-item-details.model';
import { SubsDeliveryItemDetailsService } from './subs-delivery-item-details.service';

@Component({
  templateUrl: './subs-delivery-item-details-delete-dialog.component.html'
})
export class SubsDeliveryItemDetailsDeleteDialogComponent {
  subsDeliveryItemDetails?: ISubsDeliveryItemDetails;

  constructor(
    protected subsDeliveryItemDetailsService: SubsDeliveryItemDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.subsDeliveryItemDetailsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('subsDeliveryItemDetailsListModification');
      this.activeModal.close();
    });
  }
}
