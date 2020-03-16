import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IModelGroup } from 'app/shared/model/model-group.model';
import { ModelGroupService } from './model-group.service';

@Component({
  templateUrl: './model-group-delete-dialog.component.html'
})
export class ModelGroupDeleteDialogComponent {
  modelGroup?: IModelGroup;

  constructor(
    protected modelGroupService: ModelGroupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.modelGroupService.delete(id).subscribe(() => {
      this.eventManager.broadcast('modelGroupListModification');
      this.activeModal.close();
    });
  }
}
