import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'certificate',
        loadChildren: () => import('./certificate/certificate.module').then(m => m.JhipsterSampleApplicationCertificateModule),
      },
      {
        path: 'certificate-emergency-text',
        loadChildren: () =>
          import('./certificate-emergency-text/certificate-emergency-text.module').then(
            m => m.JhipsterSampleApplicationCertificateEmergencyTextModule
          ),
      },
      {
        path: 'config',
        loadChildren: () => import('./config/config.module').then(m => m.JhipsterSampleApplicationConfigModule),
      },
      {
        path: 'document',
        loadChildren: () => import('./document/document.module').then(m => m.JhipsterSampleApplicationDocumentModule),
      },
      {
        path: 'email',
        loadChildren: () => import('./email/email.module').then(m => m.JhipsterSampleApplicationEmailModule),
      },
      {
        path: 'emergency-procedure',
        loadChildren: () =>
          import('./emergency-procedure/emergency-procedure.module').then(m => m.JhipsterSampleApplicationEmergencyProcedureModule),
      },
      {
        path: 'rezma-entity',
        loadChildren: () => import('./rezma-entity/rezma-entity.module').then(m => m.JhipsterSampleApplicationRezmaEntityModule),
      },
      {
        path: 'inspection',
        loadChildren: () => import('./inspection/inspection.module').then(m => m.JhipsterSampleApplicationInspectionModule),
      },
      {
        path: 'module-configuration',
        loadChildren: () =>
          import('./module-configuration/module-configuration.module').then(m => m.JhipsterSampleApplicationModuleConfigurationModule),
      },
      {
        path: 'obligation',
        loadChildren: () => import('./obligation/obligation.module').then(m => m.JhipsterSampleApplicationObligationModule),
      },
      {
        path: 'schedule-lock',
        loadChildren: () => import('./schedule-lock/schedule-lock.module').then(m => m.JhipsterSampleApplicationScheduleLockModule),
      },
      {
        path: 'transaction',
        loadChildren: () => import('./transaction/transaction.module').then(m => m.JhipsterSampleApplicationTransactionModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class JhipsterSampleApplicationEntityModule {}
