import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICustDocDataStore } from 'app/shared/model/cust-doc-data-store.model';
import { CustDocDataStoreService } from './cust-doc-data-store.service';

@Component({
  templateUrl: './cust-doc-data-store-delete-dialog.component.html'
})
export class CustDocDataStoreDeleteDialogComponent {
  custDocDataStore?: ICustDocDataStore;

  constructor(
    protected custDocDataStoreService: CustDocDataStoreService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.custDocDataStoreService.delete(id).subscribe(() => {
      this.eventManager.broadcast('custDocDataStoreListModification');
      this.activeModal.close();
    });
  }
}
