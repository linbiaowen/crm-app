import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGroupEndReason } from 'app/shared/model/group-end-reason.model';
import { GroupEndReasonService } from './group-end-reason.service';

@Component({
  templateUrl: './group-end-reason-delete-dialog.component.html'
})
export class GroupEndReasonDeleteDialogComponent {
  groupEndReason?: IGroupEndReason;

  constructor(
    protected groupEndReasonService: GroupEndReasonService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.groupEndReasonService.delete(id).subscribe(() => {
      this.eventManager.broadcast('groupEndReasonListModification');
      this.activeModal.close();
    });
  }
}
