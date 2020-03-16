import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISubscriptionGroup } from 'app/shared/model/subscription-group.model';
import { SubscriptionGroupService } from './subscription-group.service';

@Component({
  templateUrl: './subscription-group-delete-dialog.component.html'
})
export class SubscriptionGroupDeleteDialogComponent {
  subscriptionGroup?: ISubscriptionGroup;

  constructor(
    protected subscriptionGroupService: SubscriptionGroupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.subscriptionGroupService.delete(id).subscribe(() => {
      this.eventManager.broadcast('subscriptionGroupListModification');
      this.activeModal.close();
    });
  }
}
