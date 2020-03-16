import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { CustDocumentComponent } from './cust-document.component';
import { CustDocumentDetailComponent } from './cust-document-detail.component';
import { CustDocumentUpdateComponent } from './cust-document-update.component';
import { CustDocumentDeleteDialogComponent } from './cust-document-delete-dialog.component';
import { custDocumentRoute } from './cust-document.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(custDocumentRoute)],
  declarations: [CustDocumentComponent, CustDocumentDetailComponent, CustDocumentUpdateComponent, CustDocumentDeleteDialogComponent],
  entryComponents: [CustDocumentDeleteDialogComponent]
})
export class CrmwebCustDocumentModule {}
