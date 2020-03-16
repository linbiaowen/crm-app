import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { SubscriptionProvisionComponent } from './subscription-provision.component';
import { SubscriptionProvisionDetailComponent } from './subscription-provision-detail.component';
import { SubscriptionProvisionUpdateComponent } from './subscription-provision-update.component';
import { SubscriptionProvisionDeleteDialogComponent } from './subscription-provision-delete-dialog.component';
import { subscriptionProvisionRoute } from './subscription-provision.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(subscriptionProvisionRoute)],
  declarations: [
    SubscriptionProvisionComponent,
    SubscriptionProvisionDetailComponent,
    SubscriptionProvisionUpdateComponent,
    SubscriptionProvisionDeleteDialogComponent
  ],
  entryComponents: [SubscriptionProvisionDeleteDialogComponent]
})
export class CrmwebSubscriptionProvisionModule {}
