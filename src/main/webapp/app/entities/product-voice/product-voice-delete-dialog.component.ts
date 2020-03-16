import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProductVoice } from 'app/shared/model/product-voice.model';
import { ProductVoiceService } from './product-voice.service';

@Component({
  templateUrl: './product-voice-delete-dialog.component.html'
})
export class ProductVoiceDeleteDialogComponent {
  productVoice?: IProductVoice;

  constructor(
    protected productVoiceService: ProductVoiceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.productVoiceService.delete(id).subscribe(() => {
      this.eventManager.broadcast('productVoiceListModification');
      this.activeModal.close();
    });
  }
}
