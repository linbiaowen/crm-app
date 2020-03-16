import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { OrderMasterComponent } from './order-master.component';
import { OrderMasterDetailComponent } from './order-master-detail.component';
import { OrderMasterUpdateComponent } from './order-master-update.component';
import { OrderMasterDeleteDialogComponent } from './order-master-delete-dialog.component';
import { orderMasterRoute } from './order-master.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(orderMasterRoute)],
  declarations: [OrderMasterComponent, OrderMasterDetailComponent, OrderMasterUpdateComponent, OrderMasterDeleteDialogComponent],
  entryComponents: [OrderMasterDeleteDialogComponent]
})
export class CrmwebOrderMasterModule {}
