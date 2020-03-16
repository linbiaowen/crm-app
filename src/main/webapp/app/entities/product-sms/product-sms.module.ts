import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { ProductSmsComponent } from './product-sms.component';
import { ProductSmsDetailComponent } from './product-sms-detail.component';
import { ProductSmsUpdateComponent } from './product-sms-update.component';
import { ProductSmsDeleteDialogComponent } from './product-sms-delete-dialog.component';
import { productSmsRoute } from './product-sms.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(productSmsRoute)],
  declarations: [ProductSmsComponent, ProductSmsDetailComponent, ProductSmsUpdateComponent, ProductSmsDeleteDialogComponent],
  entryComponents: [ProductSmsDeleteDialogComponent]
})
export class CrmwebProductSmsModule {}
