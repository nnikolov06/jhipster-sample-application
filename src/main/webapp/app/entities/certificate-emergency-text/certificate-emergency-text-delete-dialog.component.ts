import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICertificateEmergencyText } from 'app/shared/model/certificate-emergency-text.model';
import { CertificateEmergencyTextService } from './certificate-emergency-text.service';

@Component({
  templateUrl: './certificate-emergency-text-delete-dialog.component.html',
})
export class CertificateEmergencyTextDeleteDialogComponent {
  certificateEmergencyText?: ICertificateEmergencyText;

  constructor(
    protected certificateEmergencyTextService: CertificateEmergencyTextService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.certificateEmergencyTextService.delete(id).subscribe(() => {
      this.eventManager.broadcast('certificateEmergencyTextListModification');
      this.activeModal.close();
    });
  }
}
