import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { CertificateEmergencyTextComponent } from './certificate-emergency-text.component';
import { CertificateEmergencyTextDetailComponent } from './certificate-emergency-text-detail.component';
import { CertificateEmergencyTextUpdateComponent } from './certificate-emergency-text-update.component';
import { CertificateEmergencyTextDeleteDialogComponent } from './certificate-emergency-text-delete-dialog.component';
import { certificateEmergencyTextRoute } from './certificate-emergency-text.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(certificateEmergencyTextRoute)],
  declarations: [
    CertificateEmergencyTextComponent,
    CertificateEmergencyTextDetailComponent,
    CertificateEmergencyTextUpdateComponent,
    CertificateEmergencyTextDeleteDialogComponent,
  ],
  entryComponents: [CertificateEmergencyTextDeleteDialogComponent],
})
export class JhipsterSampleApplicationCertificateEmergencyTextModule {}
