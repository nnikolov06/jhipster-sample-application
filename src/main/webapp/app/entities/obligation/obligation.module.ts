import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { ObligationComponent } from './obligation.component';
import { ObligationDetailComponent } from './obligation-detail.component';
import { ObligationUpdateComponent } from './obligation-update.component';
import { ObligationDeleteDialogComponent } from './obligation-delete-dialog.component';
import { obligationRoute } from './obligation.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(obligationRoute)],
  declarations: [ObligationComponent, ObligationDetailComponent, ObligationUpdateComponent, ObligationDeleteDialogComponent],
  entryComponents: [ObligationDeleteDialogComponent],
})
export class JhipsterSampleApplicationObligationModule {}
