import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { CustAddressComponent } from './cust-address.component';
import { CustAddressDetailComponent } from './cust-address-detail.component';
import { CustAddressUpdateComponent } from './cust-address-update.component';
import { CustAddressDeleteDialogComponent } from './cust-address-delete-dialog.component';
import { custAddressRoute } from './cust-address.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(custAddressRoute)],
  declarations: [CustAddressComponent, CustAddressDetailComponent, CustAddressUpdateComponent, CustAddressDeleteDialogComponent],
  entryComponents: [CustAddressDeleteDialogComponent]
})
export class CrmwebCustAddressModule {}
