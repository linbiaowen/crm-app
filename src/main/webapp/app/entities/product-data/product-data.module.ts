import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { ProductDataComponent } from './product-data.component';
import { ProductDataDetailComponent } from './product-data-detail.component';
import { ProductDataUpdateComponent } from './product-data-update.component';
import { ProductDataDeleteDialogComponent } from './product-data-delete-dialog.component';
import { productDataRoute } from './product-data.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(productDataRoute)],
  declarations: [ProductDataComponent, ProductDataDetailComponent, ProductDataUpdateComponent, ProductDataDeleteDialogComponent],
  entryComponents: [ProductDataDeleteDialogComponent]
})
export class CrmwebProductDataModule {}
