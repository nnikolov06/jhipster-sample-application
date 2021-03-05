import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { ModuleConfigurationComponent } from './module-configuration.component';
import { ModuleConfigurationDetailComponent } from './module-configuration-detail.component';
import { ModuleConfigurationUpdateComponent } from './module-configuration-update.component';
import { ModuleConfigurationDeleteDialogComponent } from './module-configuration-delete-dialog.component';
import { moduleConfigurationRoute } from './module-configuration.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(moduleConfigurationRoute)],
  declarations: [
    ModuleConfigurationComponent,
    ModuleConfigurationDetailComponent,
    ModuleConfigurationUpdateComponent,
    ModuleConfigurationDeleteDialogComponent,
  ],
  entryComponents: [ModuleConfigurationDeleteDialogComponent],
})
export class JhipsterSampleApplicationModuleConfigurationModule {}
