import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { CfsServiceSpecComponent } from './cfs-service-spec.component';
import { CfsServiceSpecDetailComponent } from './cfs-service-spec-detail.component';
import { CfsServiceSpecUpdateComponent } from './cfs-service-spec-update.component';
import { CfsServiceSpecDeleteDialogComponent } from './cfs-service-spec-delete-dialog.component';
import { cfsServiceSpecRoute } from './cfs-service-spec.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(cfsServiceSpecRoute)],
  declarations: [
    CfsServiceSpecComponent,
    CfsServiceSpecDetailComponent,
    CfsServiceSpecUpdateComponent,
    CfsServiceSpecDeleteDialogComponent
  ],
  entryComponents: [CfsServiceSpecDeleteDialogComponent]
})
export class CrmwebCfsServiceSpecModule {}
