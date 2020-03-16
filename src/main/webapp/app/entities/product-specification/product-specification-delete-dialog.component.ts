import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProductSpecification } from 'app/shared/model/product-specification.model';
import { ProductSpecificationService } from './product-specification.service';

@Component({
  templateUrl: './product-specification-delete-dialog.component.html'
})
export class ProductSpecificationDeleteDialogComponent {
  productSpecification?: IProductSpecification;

  constructor(
    protected productSpecificationService: ProductSpecificationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.productSpecificationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('productSpecificationListModification');
      this.activeModal.close();
    });
  }
}
