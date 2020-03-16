import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { ProductVoiceComponent } from './product-voice.component';
import { ProductVoiceDetailComponent } from './product-voice-detail.component';
import { ProductVoiceUpdateComponent } from './product-voice-update.component';
import { ProductVoiceDeleteDialogComponent } from './product-voice-delete-dialog.component';
import { productVoiceRoute } from './product-voice.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(productVoiceRoute)],
  declarations: [ProductVoiceComponent, ProductVoiceDetailComponent, ProductVoiceUpdateComponent, ProductVoiceDeleteDialogComponent],
  entryComponents: [ProductVoiceDeleteDialogComponent]
})
export class CrmwebProductVoiceModule {}
