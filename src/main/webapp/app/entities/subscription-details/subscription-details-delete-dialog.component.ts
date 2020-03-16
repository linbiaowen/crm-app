import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISubscriptionDetails } from 'app/shared/model/subscription-details.model';
import { SubscriptionDetailsService } from './subscription-details.service';

@Component({
  templateUrl: './subscription-details-delete-dialog.component.html'
})
export class SubscriptionDetailsDeleteDialogComponent {
  subscriptionDetails?: ISubscriptionDetails;

  constructor(
    protected subscriptionDetailsService: SubscriptionDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.subscriptionDetailsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('subscriptionDetailsListModification');
      this.activeModal.close();
    });
  }
}
