import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICommOptoutType } from 'app/shared/model/comm-optout-type.model';
import { CommOptoutTypeService } from './comm-optout-type.service';

@Component({
  templateUrl: './comm-optout-type-delete-dialog.component.html'
})
export class CommOptoutTypeDeleteDialogComponent {
  commOptoutType?: ICommOptoutType;

  constructor(
    protected commOptoutTypeService: CommOptoutTypeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.commOptoutTypeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('commOptoutTypeListModification');
      this.activeModal.close();
    });
  }
}
