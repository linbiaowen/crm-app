import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { OfferDiscountComponent } from './offer-discount.component';
import { OfferDiscountDetailComponent } from './offer-discount-detail.component';
import { OfferDiscountUpdateComponent } from './offer-discount-update.component';
import { OfferDiscountDeleteDialogComponent } from './offer-discount-delete-dialog.component';
import { offerDiscountRoute } from './offer-discount.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(offerDiscountRoute)],
  declarations: [OfferDiscountComponent, OfferDiscountDetailComponent, OfferDiscountUpdateComponent, OfferDiscountDeleteDialogComponent],
  entryComponents: [OfferDiscountDeleteDialogComponent]
})
export class CrmwebOfferDiscountModule {}
