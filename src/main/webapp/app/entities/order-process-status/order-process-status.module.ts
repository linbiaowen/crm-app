import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { OrderProcessStatusComponent } from './order-process-status.component';
import { OrderProcessStatusDetailComponent } from './order-process-status-detail.component';
import { OrderProcessStatusUpdateComponent } from './order-process-status-update.component';
import { OrderProcessStatusDeleteDialogComponent } from './order-process-status-delete-dialog.component';
import { orderProcessStatusRoute } from './order-process-status.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(orderProcessStatusRoute)],
  declarations: [
    OrderProcessStatusComponent,
    OrderProcessStatusDetailComponent,
    OrderProcessStatusUpdateComponent,
    OrderProcessStatusDeleteDialogComponent
  ],
  entryComponents: [OrderProcessStatusDeleteDialogComponent]
})
export class CrmwebOrderProcessStatusModule {}
