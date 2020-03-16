import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGroupType } from 'app/shared/model/group-type.model';
import { GroupTypeService } from './group-type.service';

@Component({
  templateUrl: './group-type-delete-dialog.component.html'
})
export class GroupTypeDeleteDialogComponent {
  groupType?: IGroupType;

  constructor(protected groupTypeService: GroupTypeService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.groupTypeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('groupTypeListModification');
      this.activeModal.close();
    });
  }
}
