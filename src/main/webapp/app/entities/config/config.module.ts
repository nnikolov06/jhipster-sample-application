import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { ConfigComponent } from './config.component';
import { ConfigDetailComponent } from './config-detail.component';
import { ConfigUpdateComponent } from './config-update.component';
import { ConfigDeleteDialogComponent } from './config-delete-dialog.component';
import { configRoute } from './config.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(configRoute)],
  declarations: [ConfigComponent, ConfigDetailComponent, ConfigUpdateComponent, ConfigDeleteDialogComponent],
  entryComponents: [ConfigDeleteDialogComponent],
})
export class JhipsterSampleApplicationConfigModule {}
