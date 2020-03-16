import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { SubsOrderDetailsComponent } from './subs-order-details.component';
import { SubsOrderDetailsDetailComponent } from './subs-order-details-detail.component';
import { SubsOrderDetailsUpdateComponent } from './subs-order-details-update.component';
import { SubsOrderDetailsDeleteDialogComponent } from './subs-order-details-delete-dialog.component';
import { subsOrderDetailsRoute } from './subs-order-details.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(subsOrderDetailsRoute)],
  declarations: [
    SubsOrderDetailsComponent,
    SubsOrderDetailsDetailComponent,
    SubsOrderDetailsUpdateComponent,
    SubsOrderDetailsDeleteDialogComponent
  ],
  entryComponents: [SubsOrderDetailsDeleteDialogComponent]
})
export class CrmwebSubsOrderDetailsModule {}
