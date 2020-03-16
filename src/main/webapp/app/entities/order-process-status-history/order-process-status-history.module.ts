import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { OrderProcessStatusHistoryComponent } from './order-process-status-history.component';
import { OrderProcessStatusHistoryDetailComponent } from './order-process-status-history-detail.component';
import { OrderProcessStatusHistoryUpdateComponent } from './order-process-status-history-update.component';
import { OrderProcessStatusHistoryDeleteDialogComponent } from './order-process-status-history-delete-dialog.component';
import { orderProcessStatusHistoryRoute } from './order-process-status-history.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(orderProcessStatusHistoryRoute)],
  declarations: [
    OrderProcessStatusHistoryComponent,
    OrderProcessStatusHistoryDetailComponent,
    OrderProcessStatusHistoryUpdateComponent,
    OrderProcessStatusHistoryDeleteDialogComponent
  ],
  entryComponents: [OrderProcessStatusHistoryDeleteDialogComponent]
})
export class CrmwebOrderProcessStatusHistoryModule {}
