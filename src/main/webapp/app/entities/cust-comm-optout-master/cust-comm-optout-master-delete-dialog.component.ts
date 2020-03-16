import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICustCommOptoutMaster } from 'app/shared/model/cust-comm-optout-master.model';
import { CustCommOptoutMasterService } from './cust-comm-optout-master.service';

@Component({
  templateUrl: './cust-comm-optout-master-delete-dialog.component.html'
})
export class CustCommOptoutMasterDeleteDialogComponent {
  custCommOptoutMaster?: ICustCommOptoutMaster;

  constructor(
    protected custCommOptoutMasterService: CustCommOptoutMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.custCommOptoutMasterService.delete(id).subscribe(() => {
      this.eventManager.broadcast('custCommOptoutMasterListModification');
      this.activeModal.close();
    });
  }
}
