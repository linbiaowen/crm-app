import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { OfferAdvancePaymentComponent } from './offer-advance-payment.component';
import { OfferAdvancePaymentDetailComponent } from './offer-advance-payment-detail.component';
import { OfferAdvancePaymentUpdateComponent } from './offer-advance-payment-update.component';
import { OfferAdvancePaymentDeleteDialogComponent } from './offer-advance-payment-delete-dialog.component';
import { offerAdvancePaymentRoute } from './offer-advance-payment.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(offerAdvancePaymentRoute)],
  declarations: [
    OfferAdvancePaymentComponent,
    OfferAdvancePaymentDetailComponent,
    OfferAdvancePaymentUpdateComponent,
    OfferAdvancePaymentDeleteDialogComponent
  ],
  entryComponents: [OfferAdvancePaymentDeleteDialogComponent]
})
export class CrmwebOfferAdvancePaymentModule {}
