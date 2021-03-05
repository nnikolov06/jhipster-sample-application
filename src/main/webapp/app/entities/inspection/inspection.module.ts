import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { InspectionComponent } from './inspection.component';
import { InspectionDetailComponent } from './inspection-detail.component';
import { InspectionUpdateComponent } from './inspection-update.component';
import { InspectionDeleteDialogComponent } from './inspection-delete-dialog.component';
import { inspectionRoute } from './inspection.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(inspectionRoute)],
  declarations: [InspectionComponent, InspectionDetailComponent, InspectionUpdateComponent, InspectionDeleteDialogComponent],
  entryComponents: [InspectionDeleteDialogComponent],
})
export class JhipsterSampleApplicationInspectionModule {}
