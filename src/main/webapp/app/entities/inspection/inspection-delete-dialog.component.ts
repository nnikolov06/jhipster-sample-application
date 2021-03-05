import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInspection } from 'app/shared/model/inspection.model';
import { InspectionService } from './inspection.service';

@Component({
  templateUrl: './inspection-delete-dialog.component.html',
})
export class InspectionDeleteDialogComponent {
  inspection?: IInspection;

  constructor(
    protected inspectionService: InspectionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.inspectionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('inspectionListModification');
      this.activeModal.close();
    });
  }
}
