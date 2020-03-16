import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { CustCommOptoutMasterComponent } from './cust-comm-optout-master.component';
import { CustCommOptoutMasterDetailComponent } from './cust-comm-optout-master-detail.component';
import { CustCommOptoutMasterUpdateComponent } from './cust-comm-optout-master-update.component';
import { CustCommOptoutMasterDeleteDialogComponent } from './cust-comm-optout-master-delete-dialog.component';
import { custCommOptoutMasterRoute } from './cust-comm-optout-master.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(custCommOptoutMasterRoute)],
  declarations: [
    CustCommOptoutMasterComponent,
    CustCommOptoutMasterDetailComponent,
    CustCommOptoutMasterUpdateComponent,
    CustCommOptoutMasterDeleteDialogComponent
  ],
  entryComponents: [CustCommOptoutMasterDeleteDialogComponent]
})
export class CrmwebCustCommOptoutMasterModule {}
