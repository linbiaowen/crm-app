import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICommMediaType } from 'app/shared/model/comm-media-type.model';
import { CommMediaTypeService } from './comm-media-type.service';

@Component({
  templateUrl: './comm-media-type-delete-dialog.component.html'
})
export class CommMediaTypeDeleteDialogComponent {
  commMediaType?: ICommMediaType;

  constructor(
    protected commMediaTypeService: CommMediaTypeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.commMediaTypeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('commMediaTypeListModification');
      this.activeModal.close();
    });
  }
}
