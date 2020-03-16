import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { CommMediaTypeComponent } from './comm-media-type.component';
import { CommMediaTypeDetailComponent } from './comm-media-type-detail.component';
import { CommMediaTypeUpdateComponent } from './comm-media-type-update.component';
import { CommMediaTypeDeleteDialogComponent } from './comm-media-type-delete-dialog.component';
import { commMediaTypeRoute } from './comm-media-type.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(commMediaTypeRoute)],
  declarations: [CommMediaTypeComponent, CommMediaTypeDetailComponent, CommMediaTypeUpdateComponent, CommMediaTypeDeleteDialogComponent],
  entryComponents: [CommMediaTypeDeleteDialogComponent]
})
export class CrmwebCommMediaTypeModule {}
