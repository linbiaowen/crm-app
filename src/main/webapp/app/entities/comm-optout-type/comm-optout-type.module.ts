import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { CommOptoutTypeComponent } from './comm-optout-type.component';
import { CommOptoutTypeDetailComponent } from './comm-optout-type-detail.component';
import { CommOptoutTypeUpdateComponent } from './comm-optout-type-update.component';
import { CommOptoutTypeDeleteDialogComponent } from './comm-optout-type-delete-dialog.component';
import { commOptoutTypeRoute } from './comm-optout-type.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(commOptoutTypeRoute)],
  declarations: [
    CommOptoutTypeComponent,
    CommOptoutTypeDetailComponent,
    CommOptoutTypeUpdateComponent,
    CommOptoutTypeDeleteDialogComponent
  ],
  entryComponents: [CommOptoutTypeDeleteDialogComponent]
})
export class CrmwebCommOptoutTypeModule {}
