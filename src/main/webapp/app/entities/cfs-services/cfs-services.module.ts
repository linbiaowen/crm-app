import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { CfsServicesComponent } from './cfs-services.component';
import { CfsServicesDetailComponent } from './cfs-services-detail.component';
import { CfsServicesUpdateComponent } from './cfs-services-update.component';
import { CfsServicesDeleteDialogComponent } from './cfs-services-delete-dialog.component';
import { cfsServicesRoute } from './cfs-services.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(cfsServicesRoute)],
  declarations: [CfsServicesComponent, CfsServicesDetailComponent, CfsServicesUpdateComponent, CfsServicesDeleteDialogComponent],
  entryComponents: [CfsServicesDeleteDialogComponent]
})
export class CrmwebCfsServicesModule {}
