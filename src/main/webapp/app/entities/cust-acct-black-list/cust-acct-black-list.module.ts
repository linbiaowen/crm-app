import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { CustAcctBlackListComponent } from './cust-acct-black-list.component';
import { CustAcctBlackListDetailComponent } from './cust-acct-black-list-detail.component';
import { CustAcctBlackListUpdateComponent } from './cust-acct-black-list-update.component';
import { CustAcctBlackListDeleteDialogComponent } from './cust-acct-black-list-delete-dialog.component';
import { custAcctBlackListRoute } from './cust-acct-black-list.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(custAcctBlackListRoute)],
  declarations: [
    CustAcctBlackListComponent,
    CustAcctBlackListDetailComponent,
    CustAcctBlackListUpdateComponent,
    CustAcctBlackListDeleteDialogComponent
  ],
  entryComponents: [CustAcctBlackListDeleteDialogComponent]
})
export class CrmwebCustAcctBlackListModule {}
