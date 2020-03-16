import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICustSubscription } from 'app/shared/model/cust-subscription.model';
import { CustSubscriptionService } from './cust-subscription.service';

@Component({
  templateUrl: './cust-subscription-delete-dialog.component.html'
})
export class CustSubscriptionDeleteDialogComponent {
  custSubscription?: ICustSubscription;

  constructor(
    protected custSubscriptionService: CustSubscriptionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.custSubscriptionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('custSubscriptionListModification');
      this.activeModal.close();
    });
  }
}
