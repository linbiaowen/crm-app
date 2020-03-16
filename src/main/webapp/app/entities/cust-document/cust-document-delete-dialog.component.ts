import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICustDocument } from 'app/shared/model/cust-document.model';
import { CustDocumentService } from './cust-document.service';

@Component({
  templateUrl: './cust-document-delete-dialog.component.html'
})
export class CustDocumentDeleteDialogComponent {
  custDocument?: ICustDocument;

  constructor(
    protected custDocumentService: CustDocumentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.custDocumentService.delete(id).subscribe(() => {
      this.eventManager.broadcast('custDocumentListModification');
      this.activeModal.close();
    });
  }
}
