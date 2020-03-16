import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { SubscriptionDetailsComponent } from './subscription-details.component';
import { SubscriptionDetailsDetailComponent } from './subscription-details-detail.component';
import { SubscriptionDetailsUpdateComponent } from './subscription-details-update.component';
import { SubscriptionDetailsDeleteDialogComponent } from './subscription-details-delete-dialog.component';
import { subscriptionDetailsRoute } from './subscription-details.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(subscriptionDetailsRoute)],
  declarations: [
    SubscriptionDetailsComponent,
    SubscriptionDetailsDetailComponent,
    SubscriptionDetailsUpdateComponent,
    SubscriptionDetailsDeleteDialogComponent
  ],
  entryComponents: [SubscriptionDetailsDeleteDialogComponent]
})
export class CrmwebSubscriptionDetailsModule {}
