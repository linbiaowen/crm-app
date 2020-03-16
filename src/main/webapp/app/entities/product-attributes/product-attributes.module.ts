import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { ProductAttributesComponent } from './product-attributes.component';
import { ProductAttributesDetailComponent } from './product-attributes-detail.component';
import { ProductAttributesUpdateComponent } from './product-attributes-update.component';
import { ProductAttributesDeleteDialogComponent } from './product-attributes-delete-dialog.component';
import { productAttributesRoute } from './product-attributes.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(productAttributesRoute)],
  declarations: [
    ProductAttributesComponent,
    ProductAttributesDetailComponent,
    ProductAttributesUpdateComponent,
    ProductAttributesDeleteDialogComponent
  ],
  entryComponents: [ProductAttributesDeleteDialogComponent]
})
export class CrmwebProductAttributesModule {}
