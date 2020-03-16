import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICfsServiceSpec } from 'app/shared/model/cfs-service-spec.model';
import { CfsServiceSpecService } from './cfs-service-spec.service';

@Component({
  templateUrl: './cfs-service-spec-delete-dialog.component.html'
})
export class CfsServiceSpecDeleteDialogComponent {
  cfsServiceSpec?: ICfsServiceSpec;

  constructor(
    protected cfsServiceSpecService: CfsServiceSpecService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.cfsServiceSpecService.delete(id).subscribe(() => {
      this.eventManager.broadcast('cfsServiceSpecListModification');
      this.activeModal.close();
    });
  }
}
