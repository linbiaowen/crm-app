import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { ProductBoxTypeComponent } from './product-box-type.component';
import { ProductBoxTypeDetailComponent } from './product-box-type-detail.component';
import { ProductBoxTypeUpdateComponent } from './product-box-type-update.component';
import { ProductBoxTypeDeleteDialogComponent } from './product-box-type-delete-dialog.component';
import { productBoxTypeRoute } from './product-box-type.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(productBoxTypeRoute)],
  declarations: [
    ProductBoxTypeComponent,
    ProductBoxTypeDetailComponent,
    ProductBoxTypeUpdateComponent,
    ProductBoxTypeDeleteDialogComponent
  ],
  entryComponents: [ProductBoxTypeDeleteDialogComponent]
})
export class CrmwebProductBoxTypeModule {}
