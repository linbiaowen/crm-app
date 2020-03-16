import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { SubscriptionProductComponent } from './subscription-product.component';
import { SubscriptionProductDetailComponent } from './subscription-product-detail.component';
import { SubscriptionProductUpdateComponent } from './subscription-product-update.component';
import { SubscriptionProductDeleteDialogComponent } from './subscription-product-delete-dialog.component';
import { subscriptionProductRoute } from './subscription-product.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(subscriptionProductRoute)],
  declarations: [
    SubscriptionProductComponent,
    SubscriptionProductDetailComponent,
    SubscriptionProductUpdateComponent,
    SubscriptionProductDeleteDialogComponent
  ],
  entryComponents: [SubscriptionProductDeleteDialogComponent]
})
export class CrmwebSubscriptionProductModule {}
