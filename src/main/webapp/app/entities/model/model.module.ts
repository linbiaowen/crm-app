import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { ModelComponent } from './model.component';
import { ModelDetailComponent } from './model-detail.component';
import { ModelUpdateComponent } from './model-update.component';
import { ModelDeleteDialogComponent } from './model-delete-dialog.component';
import { modelRoute } from './model.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(modelRoute)],
  declarations: [ModelComponent, ModelDetailComponent, ModelUpdateComponent, ModelDeleteDialogComponent],
  entryComponents: [ModelDeleteDialogComponent]
})
export class CrmwebModelModule {}
