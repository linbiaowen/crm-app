import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { ModelGroupComponent } from './model-group.component';
import { ModelGroupDetailComponent } from './model-group-detail.component';
import { ModelGroupUpdateComponent } from './model-group-update.component';
import { ModelGroupDeleteDialogComponent } from './model-group-delete-dialog.component';
import { modelGroupRoute } from './model-group.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(modelGroupRoute)],
  declarations: [ModelGroupComponent, ModelGroupDetailComponent, ModelGroupUpdateComponent, ModelGroupDeleteDialogComponent],
  entryComponents: [ModelGroupDeleteDialogComponent]
})
export class CrmwebModelGroupModule {}
