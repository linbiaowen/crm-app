import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmwebSharedModule } from 'app/shared/shared.module';
import { SupremeMasterComponent } from './supreme-master.component';
import { SupremeMasterDetailComponent } from './supreme-master-detail.component';
import { SupremeMasterUpdateComponent } from './supreme-master-update.component';
import { SupremeMasterDeleteDialogComponent } from './supreme-master-delete-dialog.component';
import { supremeMasterRoute } from './supreme-master.route';

@NgModule({
  imports: [CrmwebSharedModule, RouterModule.forChild(supremeMasterRoute)],
  declarations: [SupremeMasterComponent, SupremeMasterDetailComponent, SupremeMasterUpdateComponent, SupremeMasterDeleteDialogComponent],
  entryComponents: [SupremeMasterDeleteDialogComponent]
})
export class CrmwebSupremeMasterModule {}
