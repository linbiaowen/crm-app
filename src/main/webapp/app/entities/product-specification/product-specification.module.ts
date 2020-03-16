import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { ProductSpecificationComponent } from './product-specification.component';
import { ProductSpecificationDetailComponent } from './product-specification-detail.component';
import { ProductSpecificationUpdateComponent } from './product-specification-update.component';
import { ProductSpecificationDeleteDialogComponent } from './product-specification-delete-dialog.component';
import { productSpecificationRoute } from './product-specification.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(productSpecificationRoute)],
  declarations: [
    ProductSpecificationComponent,
    ProductSpecificationDetailComponent,
    ProductSpecificationUpdateComponent,
    ProductSpecificationDeleteDialogComponent
  ],
  entryComponents: [ProductSpecificationDeleteDialogComponent]
})
export class CrmwebProductSpecificationModule {}
