import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProductBoxType } from 'app/shared/model/product-box-type.model';
import { ProductBoxTypeService } from './product-box-type.service';

@Component({
  templateUrl: './product-box-type-delete-dialog.component.html'
})
export class ProductBoxTypeDeleteDialogComponent {
  productBoxType?: IProductBoxType;

  constructor(
    protected productBoxTypeService: ProductBoxTypeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.productBoxTypeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('productBoxTypeListModification');
      this.activeModal.close();
    });
  }
}
