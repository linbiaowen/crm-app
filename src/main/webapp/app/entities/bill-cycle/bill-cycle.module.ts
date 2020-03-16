import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { BillCycleComponent } from './bill-cycle.component';
import { BillCycleDetailComponent } from './bill-cycle-detail.component';
import { BillCycleUpdateComponent } from './bill-cycle-update.component';
import { BillCycleDeleteDialogComponent } from './bill-cycle-delete-dialog.component';
import { billCycleRoute } from './bill-cycle.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(billCycleRoute)],
  declarations: [BillCycleComponent, BillCycleDetailComponent, BillCycleUpdateComponent, BillCycleDeleteDialogComponent],
  entryComponents: [BillCycleDeleteDialogComponent]
})
export class CrmwebBillCycleModule {}
