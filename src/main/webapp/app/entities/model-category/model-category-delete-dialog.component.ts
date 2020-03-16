import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IModelCategory } from 'app/shared/model/model-category.model';
import { ModelCategoryService } from './model-category.service';

@Component({
  templateUrl: './model-category-delete-dialog.component.html'
})
export class ModelCategoryDeleteDialogComponent {
  modelCategory?: IModelCategory;

  constructor(
    protected modelCategoryService: ModelCategoryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.modelCategoryService.delete(id).subscribe(() => {
      this.eventManager.broadcast('modelCategoryListModification');
      this.activeModal.close();
    });
  }
}
