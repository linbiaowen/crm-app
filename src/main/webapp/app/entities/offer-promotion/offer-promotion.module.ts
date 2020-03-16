import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { OfferPromotionComponent } from './offer-promotion.component';
import { OfferPromotionDetailComponent } from './offer-promotion-detail.component';
import { OfferPromotionUpdateComponent } from './offer-promotion-update.component';
import { OfferPromotionDeleteDialogComponent } from './offer-promotion-delete-dialog.component';
import { offerPromotionRoute } from './offer-promotion.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(offerPromotionRoute)],
  declarations: [
    OfferPromotionComponent,
    OfferPromotionDetailComponent,
    OfferPromotionUpdateComponent,
    OfferPromotionDeleteDialogComponent
  ],
  entryComponents: [OfferPromotionDeleteDialogComponent]
})
export class CrmwebOfferPromotionModule {}
