import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { SimInventoryComponent } from './sim-inventory.component';
import { SimInventoryDetailComponent } from './sim-inventory-detail.component';
import { SimInventoryUpdateComponent } from './sim-inventory-update.component';
import { SimInventoryDeleteDialogComponent } from './sim-inventory-delete-dialog.component';
import { simInventoryRoute } from './sim-inventory.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(simInventoryRoute)],
  declarations: [SimInventoryComponent, SimInventoryDetailComponent, SimInventoryUpdateComponent, SimInventoryDeleteDialogComponent],
  entryComponents: [SimInventoryDeleteDialogComponent]
})
export class CrmwebSimInventoryModule {}
