import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRezmaEntity } from 'app/shared/model/rezma-entity.model';
import { RezmaEntityService } from './rezma-entity.service';

@Component({
  templateUrl: './rezma-entity-delete-dialog.component.html',
})
export class RezmaEntityDeleteDialogComponent {
  rezmaEntity?: IRezmaEntity;

  constructor(
    protected rezmaEntityService: RezmaEntityService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.rezmaEntityService.delete(id).subscribe(() => {
      this.eventManager.broadcast('rezmaEntityListModification');
      this.activeModal.close();
    });
  }
}
