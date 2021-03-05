import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { ScheduleLockComponent } from './schedule-lock.component';
import { ScheduleLockDetailComponent } from './schedule-lock-detail.component';
import { ScheduleLockUpdateComponent } from './schedule-lock-update.component';
import { ScheduleLockDeleteDialogComponent } from './schedule-lock-delete-dialog.component';
import { scheduleLockRoute } from './schedule-lock.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(scheduleLockRoute)],
  declarations: [ScheduleLockComponent, ScheduleLockDetailComponent, ScheduleLockUpdateComponent, ScheduleLockDeleteDialogComponent],
  entryComponents: [ScheduleLockDeleteDialogComponent],
})
export class JhipsterSampleApplicationScheduleLockModule {}
