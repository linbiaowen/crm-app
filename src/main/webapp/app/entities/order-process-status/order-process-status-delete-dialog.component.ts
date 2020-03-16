import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrderProcessStatus } from 'app/shared/model/order-process-status.model';
import { OrderProcessStatusService } from './order-process-status.service';

@Component({
  templateUrl: './order-process-status-delete-dialog.component.html'
})
export class OrderProcessStatusDeleteDialogComponent {
  orderProcessStatus?: IOrderProcessStatus;

  constructor(
    protected orderProcessStatusService: OrderProcessStatusService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.orderProcessStatusService.delete(id).subscribe(() => {
      this.eventManager.broadcast('orderProcessStatusListModification');
      this.activeModal.close();
    });
  }
}
