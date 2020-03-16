import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICustAcctBlackList } from 'app/shared/model/cust-acct-black-list.model';
import { CustAcctBlackListService } from './cust-acct-black-list.service';

@Component({
  templateUrl: './cust-acct-black-list-delete-dialog.component.html'
})
export class CustAcctBlackListDeleteDialogComponent {
  custAcctBlackList?: ICustAcctBlackList;

  constructor(
    protected custAcctBlackListService: CustAcctBlackListService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.custAcctBlackListService.delete(id).subscribe(() => {
      this.eventManager.broadcast('custAcctBlackListListModification');
      this.activeModal.close();
    });
  }
}
