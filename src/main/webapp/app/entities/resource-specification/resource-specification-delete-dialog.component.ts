import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IResourceSpecification } from 'app/shared/model/resource-specification.model';
import { ResourceSpecificationService } from './resource-specification.service';

@Component({
  templateUrl: './resource-specification-delete-dialog.component.html'
})
export class ResourceSpecificationDeleteDialogComponent {
  resourceSpecification?: IResourceSpecification;

  constructor(
    protected resourceSpecificationService: ResourceSpecificationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.resourceSpecificationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('resourceSpecificationListModification');
      this.activeModal.close();
    });
  }
}
