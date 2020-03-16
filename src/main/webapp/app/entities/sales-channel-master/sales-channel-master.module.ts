import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { SalesChannelMasterComponent } from './sales-channel-master.component';
import { SalesChannelMasterDetailComponent } from './sales-channel-master-detail.component';
import { SalesChannelMasterUpdateComponent } from './sales-channel-master-update.component';
import { SalesChannelMasterDeleteDialogComponent } from './sales-channel-master-delete-dialog.component';
import { salesChannelMasterRoute } from './sales-channel-master.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(salesChannelMasterRoute)],
  declarations: [
    SalesChannelMasterComponent,
    SalesChannelMasterDetailComponent,
    SalesChannelMasterUpdateComponent,
    SalesChannelMasterDeleteDialogComponent
  ],
  entryComponents: [SalesChannelMasterDeleteDialogComponent]
})
export class CrmwebSalesChannelMasterModule {}
