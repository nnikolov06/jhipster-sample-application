import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEmergencyProcedure } from 'app/shared/model/emergency-procedure.model';
import { EmergencyProcedureService } from './emergency-procedure.service';

@Component({
  templateUrl: './emergency-procedure-delete-dialog.component.html',
})
export class EmergencyProcedureDeleteDialogComponent {
  emergencyProcedure?: IEmergencyProcedure;

  constructor(
    protected emergencyProcedureService: EmergencyProcedureService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.emergencyProcedureService.delete(id).subscribe(() => {
      this.eventManager.broadcast('emergencyProcedureListModification');
      this.activeModal.close();
    });
  }
}
