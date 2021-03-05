import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { EmergencyProcedureComponent } from './emergency-procedure.component';
import { EmergencyProcedureDetailComponent } from './emergency-procedure-detail.component';
import { EmergencyProcedureUpdateComponent } from './emergency-procedure-update.component';
import { EmergencyProcedureDeleteDialogComponent } from './emergency-procedure-delete-dialog.component';
import { emergencyProcedureRoute } from './emergency-procedure.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(emergencyProcedureRoute)],
  declarations: [
    EmergencyProcedureComponent,
    EmergencyProcedureDetailComponent,
    EmergencyProcedureUpdateComponent,
    EmergencyProcedureDeleteDialogComponent,
  ],
  entryComponents: [EmergencyProcedureDeleteDialogComponent],
})
export class JhipsterSampleApplicationEmergencyProcedureModule {}
