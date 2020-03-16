import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { VoiceServiceSpecComponent } from './voice-service-spec.component';
import { VoiceServiceSpecDetailComponent } from './voice-service-spec-detail.component';
import { VoiceServiceSpecUpdateComponent } from './voice-service-spec-update.component';
import { VoiceServiceSpecDeleteDialogComponent } from './voice-service-spec-delete-dialog.component';
import { voiceServiceSpecRoute } from './voice-service-spec.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(voiceServiceSpecRoute)],
  declarations: [
    VoiceServiceSpecComponent,
    VoiceServiceSpecDetailComponent,
    VoiceServiceSpecUpdateComponent,
    VoiceServiceSpecDeleteDialogComponent
  ],
  entryComponents: [VoiceServiceSpecDeleteDialogComponent]
})
export class CrmwebVoiceServiceSpecModule {}
