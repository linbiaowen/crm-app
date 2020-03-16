import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { DeliveryOptionComponent } from './delivery-option.component';
import { DeliveryOptionDetailComponent } from './delivery-option-detail.component';
import { DeliveryOptionUpdateComponent } from './delivery-option-update.component';
import { DeliveryOptionDeleteDialogComponent } from './delivery-option-delete-dialog.component';
import { deliveryOptionRoute } from './delivery-option.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(deliveryOptionRoute)],
  declarations: [
    DeliveryOptionComponent,
    DeliveryOptionDetailComponent,
    DeliveryOptionUpdateComponent,
    DeliveryOptionDeleteDialogComponent
  ],
  entryComponents: [DeliveryOptionDeleteDialogComponent]
})
export class CrmwebDeliveryOptionModule {}
