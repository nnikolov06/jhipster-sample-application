import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { CertificateComponent } from './certificate.component';
import { CertificateDetailComponent } from './certificate-detail.component';
import { CertificateUpdateComponent } from './certificate-update.component';
import { CertificateDeleteDialogComponent } from './certificate-delete-dialog.component';
import { certificateRoute } from './certificate.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(certificateRoute)],
  declarations: [CertificateComponent, CertificateDetailComponent, CertificateUpdateComponent, CertificateDeleteDialogComponent],
  entryComponents: [CertificateDeleteDialogComponent],
})
export class JhipsterSampleApplicationCertificateModule {}
