import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { CustContactComponent } from './cust-contact.component';
import { CustContactDetailComponent } from './cust-contact-detail.component';
import { CustContactUpdateComponent } from './cust-contact-update.component';
import { CustContactDeleteDialogComponent } from './cust-contact-delete-dialog.component';
import { custContactRoute } from './cust-contact.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(custContactRoute)],
  declarations: [CustContactComponent, CustContactDetailComponent, CustContactUpdateComponent, CustContactDeleteDialogComponent],
  entryComponents: [CustContactDeleteDialogComponent]
})
export class CrmwebCustContactModule {}
