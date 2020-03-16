import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOfferSpecification } from 'app/shared/model/offer-specification.model';
import { OfferSpecificationService } from './offer-specification.service';

@Component({
  templateUrl: './offer-specification-delete-dialog.component.html'
})
export class OfferSpecificationDeleteDialogComponent {
  offerSpecification?: IOfferSpecification;

  constructor(
    protected offerSpecificationService: OfferSpecificationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.offerSpecificationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('offerSpecificationListModification');
      this.activeModal.close();
    });
  }
}
