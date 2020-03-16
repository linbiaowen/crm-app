import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVoiceServiceSpec } from 'app/shared/model/voice-service-spec.model';
import { VoiceServiceSpecService } from './voice-service-spec.service';

@Component({
  templateUrl: './voice-service-spec-delete-dialog.component.html'
})
export class VoiceServiceSpecDeleteDialogComponent {
  voiceServiceSpec?: IVoiceServiceSpec;

  constructor(
    protected voiceServiceSpecService: VoiceServiceSpecService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.voiceServiceSpecService.delete(id).subscribe(() => {
      this.eventManager.broadcast('voiceServiceSpecListModification');
      this.activeModal.close();
    });
  }
}
