import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISubsPurchaseControl } from 'app/shared/model/subs-purchase-control.model';
import { SubsPurchaseControlService } from './subs-purchase-control.service';

@Component({
  templateUrl: './subs-purchase-control-delete-dialog.component.html'
})
export class SubsPurchaseControlDeleteDialogComponent {
  subsPurchaseControl?: ISubsPurchaseControl;

  constructor(
    protected subsPurchaseControlService: SubsPurchaseControlService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.subsPurchaseControlService.delete(id).subscribe(() => {
      this.eventManager.broadcast('subsPurchaseControlListModification');
      this.activeModal.close();
    });
  }
}
