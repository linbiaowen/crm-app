import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { OrderProcessConfigComponent } from './order-process-config.component';
import { OrderProcessConfigDetailComponent } from './order-process-config-detail.component';
import { OrderProcessConfigUpdateComponent } from './order-process-config-update.component';
import { OrderProcessConfigDeleteDialogComponent } from './order-process-config-delete-dialog.component';
import { orderProcessConfigRoute } from './order-process-config.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(orderProcessConfigRoute)],
  declarations: [
    OrderProcessConfigComponent,
    OrderProcessConfigDetailComponent,
    OrderProcessConfigUpdateComponent,
    OrderProcessConfigDeleteDialogComponent
  ],
  entryComponents: [OrderProcessConfigDeleteDialogComponent]
})
export class CrmwebOrderProcessConfigModule {}
