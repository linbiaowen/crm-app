import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISupremeMaster } from 'app/shared/model/supreme-master.model';
import { SupremeMasterService } from './supreme-master.service';

@Component({
  templateUrl: './supreme-master-delete-dialog.component.html'
})
export class SupremeMasterDeleteDialogComponent {
  supremeMaster?: ISupremeMaster;

  constructor(
    protected supremeMasterService: SupremeMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.supremeMasterService.delete(id).subscribe(() => {
      this.eventManager.broadcast('supremeMasterListModification');
      this.activeModal.close();
    });
  }
}
