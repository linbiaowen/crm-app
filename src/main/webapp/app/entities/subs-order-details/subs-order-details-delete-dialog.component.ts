import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISubsOrderDetails } from 'app/shared/model/subs-order-details.model';
import { SubsOrderDetailsService } from './subs-order-details.service';

@Component({
  templateUrl: './subs-order-details-delete-dialog.component.html'
})
export class SubsOrderDetailsDeleteDialogComponent {
  subsOrderDetails?: ISubsOrderDetails;

  constructor(
    protected subsOrderDetailsService: SubsOrderDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.subsOrderDetailsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('subsOrderDetailsListModification');
      this.activeModal.close();
    });
  }
}
