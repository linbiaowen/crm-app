import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrderProcessConfig } from 'app/shared/model/order-process-config.model';
import { OrderProcessConfigService } from './order-process-config.service';

@Component({
  templateUrl: './order-process-config-delete-dialog.component.html'
})
export class OrderProcessConfigDeleteDialogComponent {
  orderProcessConfig?: IOrderProcessConfig;

  constructor(
    protected orderProcessConfigService: OrderProcessConfigService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.orderProcessConfigService.delete(id).subscribe(() => {
      this.eventManager.broadcast('orderProcessConfigListModification');
      this.activeModal.close();
    });
  }
}
