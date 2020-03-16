import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { ProductSimAttributesComponent } from './product-sim-attributes.component';
import { ProductSimAttributesDetailComponent } from './product-sim-attributes-detail.component';
import { ProductSimAttributesUpdateComponent } from './product-sim-attributes-update.component';
import { ProductSimAttributesDeleteDialogComponent } from './product-sim-attributes-delete-dialog.component';
import { productSimAttributesRoute } from './product-sim-attributes.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(productSimAttributesRoute)],
  declarations: [
    ProductSimAttributesComponent,
    ProductSimAttributesDetailComponent,
    ProductSimAttributesUpdateComponent,
    ProductSimAttributesDeleteDialogComponent
  ],
  entryComponents: [ProductSimAttributesDeleteDialogComponent]
})
export class CrmwebProductSimAttributesModule {}
