import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBillCycle } from 'app/shared/model/bill-cycle.model';
import { BillCycleService } from './bill-cycle.service';

@Component({
  templateUrl: './bill-cycle-delete-dialog.component.html'
})
export class BillCycleDeleteDialogComponent {
  billCycle?: IBillCycle;

  constructor(protected billCycleService: BillCycleService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.billCycleService.delete(id).subscribe(() => {
      this.eventManager.broadcast('billCycleListModification');
      this.activeModal.close();
    });
  }
}
