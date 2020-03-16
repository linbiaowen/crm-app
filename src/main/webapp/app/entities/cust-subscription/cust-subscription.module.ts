import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { CustSubscriptionComponent } from './cust-subscription.component';
import { CustSubscriptionDetailComponent } from './cust-subscription-detail.component';
import { CustSubscriptionUpdateComponent } from './cust-subscription-update.component';
import { CustSubscriptionDeleteDialogComponent } from './cust-subscription-delete-dialog.component';
import { custSubscriptionRoute } from './cust-subscription.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(custSubscriptionRoute)],
  declarations: [
    CustSubscriptionComponent,
    CustSubscriptionDetailComponent,
    CustSubscriptionUpdateComponent,
    CustSubscriptionDeleteDialogComponent
  ],
  entryComponents: [CustSubscriptionDeleteDialogComponent]
})
export class CrmwebCustSubscriptionModule {}
