import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProductAttributes } from 'app/shared/model/product-attributes.model';
import { ProductAttributesService } from './product-attributes.service';

@Component({
  templateUrl: './product-attributes-delete-dialog.component.html'
})
export class ProductAttributesDeleteDialogComponent {
  productAttributes?: IProductAttributes;

  constructor(
    protected productAttributesService: ProductAttributesService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.productAttributesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('productAttributesListModification');
      this.activeModal.close();
    });
  }
}
