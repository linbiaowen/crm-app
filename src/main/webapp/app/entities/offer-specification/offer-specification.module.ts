import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { OfferSpecificationComponent } from './offer-specification.component';
import { OfferSpecificationDetailComponent } from './offer-specification-detail.component';
import { OfferSpecificationUpdateComponent } from './offer-specification-update.component';
import { OfferSpecificationDeleteDialogComponent } from './offer-specification-delete-dialog.component';
import { offerSpecificationRoute } from './offer-specification.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(offerSpecificationRoute)],
  declarations: [
    OfferSpecificationComponent,
    OfferSpecificationDetailComponent,
    OfferSpecificationUpdateComponent,
    OfferSpecificationDeleteDialogComponent
  ],
  entryComponents: [OfferSpecificationDeleteDialogComponent]
})
export class CrmwebOfferSpecificationModule {}
