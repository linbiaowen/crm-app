import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICustContact } from 'app/shared/model/cust-contact.model';
import { CustContactService } from './cust-contact.service';

@Component({
  templateUrl: './cust-contact-delete-dialog.component.html'
})
export class CustContactDeleteDialogComponent {
  custContact?: ICustContact;

  constructor(
    protected custContactService: CustContactService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.custContactService.delete(id).subscribe(() => {
      this.eventManager.broadcast('custContactListModification');
      this.activeModal.close();
    });
  }
}
