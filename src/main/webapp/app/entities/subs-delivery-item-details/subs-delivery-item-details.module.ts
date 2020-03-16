import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { SubsDeliveryItemDetailsComponent } from './subs-delivery-item-details.component';
import { SubsDeliveryItemDetailsDetailComponent } from './subs-delivery-item-details-detail.component';
import { SubsDeliveryItemDetailsUpdateComponent } from './subs-delivery-item-details-update.component';
import { SubsDeliveryItemDetailsDeleteDialogComponent } from './subs-delivery-item-details-delete-dialog.component';
import { subsDeliveryItemDetailsRoute } from './subs-delivery-item-details.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(subsDeliveryItemDetailsRoute)],
  declarations: [
    SubsDeliveryItemDetailsComponent,
    SubsDeliveryItemDetailsDetailComponent,
    SubsDeliveryItemDetailsUpdateComponent,
    SubsDeliveryItemDetailsDeleteDialogComponent
  ],
  entryComponents: [SubsDeliveryItemDetailsDeleteDialogComponent]
})
export class CrmwebSubsDeliveryItemDetailsModule {}
