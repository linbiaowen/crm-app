import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISimInventory } from 'app/shared/model/sim-inventory.model';
import { SimInventoryService } from './sim-inventory.service';

@Component({
  templateUrl: './sim-inventory-delete-dialog.component.html'
})
export class SimInventoryDeleteDialogComponent {
  simInventory?: ISimInventory;

  constructor(
    protected simInventoryService: SimInventoryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.simInventoryService.delete(id).subscribe(() => {
      this.eventManager.broadcast('simInventoryListModification');
      this.activeModal.close();
    });
  }
}
