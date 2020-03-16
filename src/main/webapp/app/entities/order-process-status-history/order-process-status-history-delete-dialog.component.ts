import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrderProcessStatusHistory } from 'app/shared/model/order-process-status-history.model';
import { OrderProcessStatusHistoryService } from './order-process-status-history.service';

@Component({
  templateUrl: './order-process-status-history-delete-dialog.component.html'
})
export class OrderProcessStatusHistoryDeleteDialogComponent {
  orderProcessStatusHistory?: IOrderProcessStatusHistory;

  constructor(
    protected orderProcessStatusHistoryService: OrderProcessStatusHistoryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.orderProcessStatusHistoryService.delete(id).subscribe(() => {
      this.eventManager.broadcast('orderProcessStatusHistoryListModification');
      this.activeModal.close();
    });
  }
}
