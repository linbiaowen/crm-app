import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProductSms } from 'app/shared/model/product-sms.model';
import { ProductSmsService } from './product-sms.service';

@Component({
  templateUrl: './product-sms-delete-dialog.component.html'
})
export class ProductSmsDeleteDialogComponent {
  productSms?: IProductSms;

  constructor(
    protected productSmsService: ProductSmsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.productSmsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('productSmsListModification');
      this.activeModal.close();
    });
  }
}
