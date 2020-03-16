import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICustAddress } from 'app/shared/model/cust-address.model';
import { CustAddressService } from './cust-address.service';

@Component({
  templateUrl: './cust-address-delete-dialog.component.html'
})
export class CustAddressDeleteDialogComponent {
  custAddress?: ICustAddress;

  constructor(
    protected custAddressService: CustAddressService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.custAddressService.delete(id).subscribe(() => {
      this.eventManager.broadcast('custAddressListModification');
      this.activeModal.close();
    });
  }
}
