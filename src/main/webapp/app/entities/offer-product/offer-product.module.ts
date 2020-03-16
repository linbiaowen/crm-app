import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { OfferProductComponent } from './offer-product.component';
import { OfferProductDetailComponent } from './offer-product-detail.component';
import { OfferProductUpdateComponent } from './offer-product-update.component';
import { OfferProductDeleteDialogComponent } from './offer-product-delete-dialog.component';
import { offerProductRoute } from './offer-product.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(offerProductRoute)],
  declarations: [OfferProductComponent, OfferProductDetailComponent, OfferProductUpdateComponent, OfferProductDeleteDialogComponent],
  entryComponents: [OfferProductDeleteDialogComponent]
})
export class CrmwebOfferProductModule {}
