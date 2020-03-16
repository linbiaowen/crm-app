import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProductData } from 'app/shared/model/product-data.model';
import { ProductDataService } from './product-data.service';

@Component({
  templateUrl: './product-data-delete-dialog.component.html'
})
export class ProductDataDeleteDialogComponent {
  productData?: IProductData;

  constructor(
    protected productDataService: ProductDataService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.productDataService.delete(id).subscribe(() => {
      this.eventManager.broadcast('productDataListModification');
      this.activeModal.close();
    });
  }
}
