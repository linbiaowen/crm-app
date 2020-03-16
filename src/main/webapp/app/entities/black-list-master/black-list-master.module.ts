import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { BlackListMasterComponent } from './black-list-master.component';
import { BlackListMasterDetailComponent } from './black-list-master-detail.component';
import { BlackListMasterUpdateComponent } from './black-list-master-update.component';
import { BlackListMasterDeleteDialogComponent } from './black-list-master-delete-dialog.component';
import { blackListMasterRoute } from './black-list-master.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(blackListMasterRoute)],
  declarations: [
    BlackListMasterComponent,
    BlackListMasterDetailComponent,
    BlackListMasterUpdateComponent,
    BlackListMasterDeleteDialogComponent
  ],
  entryComponents: [BlackListMasterDeleteDialogComponent]
})
export class CrmwebBlackListMasterModule {}
