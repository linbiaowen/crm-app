import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { SubsPurchaseControlComponent } from './subs-purchase-control.component';
import { SubsPurchaseControlDetailComponent } from './subs-purchase-control-detail.component';
import { SubsPurchaseControlUpdateComponent } from './subs-purchase-control-update.component';
import { SubsPurchaseControlDeleteDialogComponent } from './subs-purchase-control-delete-dialog.component';
import { subsPurchaseControlRoute } from './subs-purchase-control.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(subsPurchaseControlRoute)],
  declarations: [
    SubsPurchaseControlComponent,
    SubsPurchaseControlDetailComponent,
    SubsPurchaseControlUpdateComponent,
    SubsPurchaseControlDeleteDialogComponent
  ],
  entryComponents: [SubsPurchaseControlDeleteDialogComponent]
})
export class CrmwebSubsPurchaseControlModule {}
