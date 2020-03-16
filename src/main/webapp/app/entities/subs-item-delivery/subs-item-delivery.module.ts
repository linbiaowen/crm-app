import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { SubsItemDeliveryComponent } from './subs-item-delivery.component';
import { SubsItemDeliveryDetailComponent } from './subs-item-delivery-detail.component';
import { SubsItemDeliveryUpdateComponent } from './subs-item-delivery-update.component';
import { SubsItemDeliveryDeleteDialogComponent } from './subs-item-delivery-delete-dialog.component';
import { subsItemDeliveryRoute } from './subs-item-delivery.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(subsItemDeliveryRoute)],
  declarations: [
    SubsItemDeliveryComponent,
    SubsItemDeliveryDetailComponent,
    SubsItemDeliveryUpdateComponent,
    SubsItemDeliveryDeleteDialogComponent
  ],
  entryComponents: [SubsItemDeliveryDeleteDialogComponent]
})
export class CrmwebSubsItemDeliveryModule {}
