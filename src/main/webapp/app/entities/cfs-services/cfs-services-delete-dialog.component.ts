import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICfsServices } from 'app/shared/model/cfs-services.model';
import { CfsServicesService } from './cfs-services.service';

@Component({
  templateUrl: './cfs-services-delete-dialog.component.html'
})
export class CfsServicesDeleteDialogComponent {
  cfsServices?: ICfsServices;

  constructor(
    protected cfsServicesService: CfsServicesService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.cfsServicesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('cfsServicesListModification');
      this.activeModal.close();
    });
  }
}
