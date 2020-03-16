import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDataServiceSpec } from 'app/shared/model/data-service-spec.model';
import { DataServiceSpecService } from './data-service-spec.service';

@Component({
  templateUrl: './data-service-spec-delete-dialog.component.html'
})
export class DataServiceSpecDeleteDialogComponent {
  dataServiceSpec?: IDataServiceSpec;

  constructor(
    protected dataServiceSpecService: DataServiceSpecService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.dataServiceSpecService.delete(id).subscribe(() => {
      this.eventManager.broadcast('dataServiceSpecListModification');
      this.activeModal.close();
    });
  }
}
