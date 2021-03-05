import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IObligation } from 'app/shared/model/obligation.model';
import { ObligationService } from './obligation.service';

@Component({
  templateUrl: './obligation-delete-dialog.component.html',
})
export class ObligationDeleteDialogComponent {
  obligation?: IObligation;

  constructor(
    protected obligationService: ObligationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.obligationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('obligationListModification');
      this.activeModal.close();
    });
  }
}
