import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { DeliveryMethodComponent } from './delivery-method.component';
import { DeliveryMethodDetailComponent } from './delivery-method-detail.component';
import { DeliveryMethodUpdateComponent } from './delivery-method-update.component';
import { DeliveryMethodDeleteDialogComponent } from './delivery-method-delete-dialog.component';
import { deliveryMethodRoute } from './delivery-method.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(deliveryMethodRoute)],
  declarations: [
    DeliveryMethodComponent,
    DeliveryMethodDetailComponent,
    DeliveryMethodUpdateComponent,
    DeliveryMethodDeleteDialogComponent
  ],
  entryComponents: [DeliveryMethodDeleteDialogComponent]
})
export class CrmwebDeliveryMethodModule {}
