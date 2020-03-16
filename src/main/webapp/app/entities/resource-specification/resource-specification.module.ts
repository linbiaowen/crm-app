import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { ResourceSpecificationComponent } from './resource-specification.component';
import { ResourceSpecificationDetailComponent } from './resource-specification-detail.component';
import { ResourceSpecificationUpdateComponent } from './resource-specification-update.component';
import { ResourceSpecificationDeleteDialogComponent } from './resource-specification-delete-dialog.component';
import { resourceSpecificationRoute } from './resource-specification.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(resourceSpecificationRoute)],
  declarations: [
    ResourceSpecificationComponent,
    ResourceSpecificationDetailComponent,
    ResourceSpecificationUpdateComponent,
    ResourceSpecificationDeleteDialogComponent
  ],
  entryComponents: [ResourceSpecificationDeleteDialogComponent]
})
export class CrmwebResourceSpecificationModule {}
