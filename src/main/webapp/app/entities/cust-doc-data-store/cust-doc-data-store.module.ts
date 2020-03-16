import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { CustDocDataStoreComponent } from './cust-doc-data-store.component';
import { CustDocDataStoreDetailComponent } from './cust-doc-data-store-detail.component';
import { CustDocDataStoreUpdateComponent } from './cust-doc-data-store-update.component';
import { CustDocDataStoreDeleteDialogComponent } from './cust-doc-data-store-delete-dialog.component';
import { custDocDataStoreRoute } from './cust-doc-data-store.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(custDocDataStoreRoute)],
  declarations: [
    CustDocDataStoreComponent,
    CustDocDataStoreDetailComponent,
    CustDocDataStoreUpdateComponent,
    CustDocDataStoreDeleteDialogComponent
  ],
  entryComponents: [CustDocDataStoreDeleteDialogComponent]
})
export class CrmwebCustDocDataStoreModule {}
