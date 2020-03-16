import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrderMaster } from 'app/shared/model/order-master.model';
import { OrderMasterService } from './order-master.service';

@Component({
  templateUrl: './order-master-delete-dialog.component.html'
})
export class OrderMasterDeleteDialogComponent {
  orderMaster?: IOrderMaster;

  constructor(
    protected orderMasterService: OrderMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.orderMasterService.delete(id).subscribe(() => {
      this.eventManager.broadcast('orderMasterListModification');
      this.activeModal.close();
    });
  }
}
