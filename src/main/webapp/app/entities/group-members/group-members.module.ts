import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { GroupMembersComponent } from './group-members.component';
import { GroupMembersDetailComponent } from './group-members-detail.component';
import { GroupMembersUpdateComponent } from './group-members-update.component';
import { GroupMembersDeleteDialogComponent } from './group-members-delete-dialog.component';
import { groupMembersRoute } from './group-members.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(groupMembersRoute)],
  declarations: [GroupMembersComponent, GroupMembersDetailComponent, GroupMembersUpdateComponent, GroupMembersDeleteDialogComponent],
  entryComponents: [GroupMembersDeleteDialogComponent]
})
export class CrmwebGroupMembersModule {}
