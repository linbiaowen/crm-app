import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { SubscriptionGroupComponent } from './subscription-group.component';
import { SubscriptionGroupDetailComponent } from './subscription-group-detail.component';
import { SubscriptionGroupUpdateComponent } from './subscription-group-update.component';
import { SubscriptionGroupDeleteDialogComponent } from './subscription-group-delete-dialog.component';
import { subscriptionGroupRoute } from './subscription-group.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(subscriptionGroupRoute)],
  declarations: [
    SubscriptionGroupComponent,
    SubscriptionGroupDetailComponent,
    SubscriptionGroupUpdateComponent,
    SubscriptionGroupDeleteDialogComponent
  ],
  entryComponents: [SubscriptionGroupDeleteDialogComponent]
})
export class CrmwebSubscriptionGroupModule {}
