import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { DataServiceSpecComponent } from './data-service-spec.component';
import { DataServiceSpecDetailComponent } from './data-service-spec-detail.component';
import { DataServiceSpecUpdateComponent } from './data-service-spec-update.component';
import { DataServiceSpecDeleteDialogComponent } from './data-service-spec-delete-dialog.component';
import { dataServiceSpecRoute } from './data-service-spec.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(dataServiceSpecRoute)],
  declarations: [
    DataServiceSpecComponent,
    DataServiceSpecDetailComponent,
    DataServiceSpecUpdateComponent,
    DataServiceSpecDeleteDialogComponent
  ],
  entryComponents: [DataServiceSpecDeleteDialogComponent]
})
export class CrmwebDataServiceSpecModule {}
