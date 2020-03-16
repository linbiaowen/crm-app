import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISalesChannelMaster } from 'app/shared/model/sales-channel-master.model';
import { SalesChannelMasterService } from './sales-channel-master.service';

@Component({
  templateUrl: './sales-channel-master-delete-dialog.component.html'
})
export class SalesChannelMasterDeleteDialogComponent {
  salesChannelMaster?: ISalesChannelMaster;

  constructor(
    protected salesChannelMasterService: SalesChannelMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.salesChannelMasterService.delete(id).subscribe(() => {
      this.eventManager.broadcast('salesChannelMasterListModification');
      this.activeModal.close();
    });
  }
}
