import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISubscriptionProvision } from 'app/shared/model/subscription-provision.model';
import { SubscriptionProvisionService } from './subscription-provision.service';

@Component({
  templateUrl: './subscription-provision-delete-dialog.component.html'
})
export class SubscriptionProvisionDeleteDialogComponent {
  subscriptionProvision?: ISubscriptionProvision;

  constructor(
    protected subscriptionProvisionService: SubscriptionProvisionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.subscriptionProvisionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('subscriptionProvisionListModification');
      this.activeModal.close();
    });
  }
}
