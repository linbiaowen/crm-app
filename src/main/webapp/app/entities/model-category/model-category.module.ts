import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { ModelCategoryComponent } from './model-category.component';
import { ModelCategoryDetailComponent } from './model-category-detail.component';
import { ModelCategoryUpdateComponent } from './model-category-update.component';
import { ModelCategoryDeleteDialogComponent } from './model-category-delete-dialog.component';
import { modelCategoryRoute } from './model-category.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(modelCategoryRoute)],
  declarations: [ModelCategoryComponent, ModelCategoryDetailComponent, ModelCategoryUpdateComponent, ModelCategoryDeleteDialogComponent],
  entryComponents: [ModelCategoryDeleteDialogComponent]
})
export class CrmwebModelCategoryModule {}
