import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBlackListMaster } from 'app/shared/model/black-list-master.model';
import { BlackListMasterService } from './black-list-master.service';

@Component({
  templateUrl: './black-list-master-delete-dialog.component.html'
})
export class BlackListMasterDeleteDialogComponent {
  blackListMaster?: IBlackListMaster;

  constructor(
    protected blackListMasterService: BlackListMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.blackListMasterService.delete(id).subscribe(() => {
      this.eventManager.broadcast('blackListMasterListModification');
      this.activeModal.close();
    });
  }
}
