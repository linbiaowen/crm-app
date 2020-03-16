import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProductSimAttributes } from 'app/shared/model/product-sim-attributes.model';
import { ProductSimAttributesService } from './product-sim-attributes.service';

@Component({
  templateUrl: './product-sim-attributes-delete-dialog.component.html'
})
export class ProductSimAttributesDeleteDialogComponent {
  productSimAttributes?: IProductSimAttributes;

  constructor(
    protected productSimAttributesService: ProductSimAttributesService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.productSimAttributesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('productSimAttributesListModification');
      this.activeModal.close();
    });
  }
}
