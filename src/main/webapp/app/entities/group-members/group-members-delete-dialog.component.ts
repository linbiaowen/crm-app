import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGroupMembers } from 'app/shared/model/group-members.model';
import { GroupMembersService } from './group-members.service';

@Component({
  templateUrl: './group-members-delete-dialog.component.html'
})
export class GroupMembersDeleteDialogComponent {
  groupMembers?: IGroupMembers;

  constructor(
    protected groupMembersService: GroupMembersService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.groupMembersService.delete(id).subscribe(() => {
      this.eventManager.broadcast('groupMembersListModification');
      this.activeModal.close();
    });
  }
}
