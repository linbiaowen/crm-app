import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { GroupEndReasonComponent } from './group-end-reason.component';
import { GroupEndReasonDetailComponent } from './group-end-reason-detail.component';
import { GroupEndReasonUpdateComponent } from './group-end-reason-update.component';
import { GroupEndReasonDeleteDialogComponent } from './group-end-reason-delete-dialog.component';
import { groupEndReasonRoute } from './group-end-reason.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(groupEndReasonRoute)],
  declarations: [
    GroupEndReasonComponent,
    GroupEndReasonDetailComponent,
    GroupEndReasonUpdateComponent,
    GroupEndReasonDeleteDialogComponent
  ],
  entryComponents: [GroupEndReasonDeleteDialogComponent]
})
export class CrmwebGroupEndReasonModule {}
