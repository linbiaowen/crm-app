import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { GroupTypeComponent } from './group-type.component';
import { GroupTypeDetailComponent } from './group-type-detail.component';
import { GroupTypeUpdateComponent } from './group-type-update.component';
import { GroupTypeDeleteDialogComponent } from './group-type-delete-dialog.component';
import { groupTypeRoute } from './group-type.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(groupTypeRoute)],
  declarations: [GroupTypeComponent, GroupTypeDetailComponent, GroupTypeUpdateComponent, GroupTypeDeleteDialogComponent],
  entryComponents: [GroupTypeDeleteDialogComponent]
})
export class CrmwebGroupTypeModule {}
