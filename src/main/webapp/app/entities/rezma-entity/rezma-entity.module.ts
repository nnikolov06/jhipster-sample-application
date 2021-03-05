import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { RezmaEntityComponent } from './rezma-entity.component';
import { RezmaEntityDetailComponent } from './rezma-entity-detail.component';
import { RezmaEntityUpdateComponent } from './rezma-entity-update.component';
import { RezmaEntityDeleteDialogComponent } from './rezma-entity-delete-dialog.component';
import { rezmaEntityRoute } from './rezma-entity.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(rezmaEntityRoute)],
  declarations: [RezmaEntityComponent, RezmaEntityDetailComponent, RezmaEntityUpdateComponent, RezmaEntityDeleteDialogComponent],
  entryComponents: [RezmaEntityDeleteDialogComponent],
})
export class JhipsterSampleApplicationRezmaEntityModule {}
