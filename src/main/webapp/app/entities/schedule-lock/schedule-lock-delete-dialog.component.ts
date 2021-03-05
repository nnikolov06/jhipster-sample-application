import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IScheduleLock } from 'app/shared/model/schedule-lock.model';
import { ScheduleLockService } from './schedule-lock.service';

@Component({
  templateUrl: './schedule-lock-delete-dialog.component.html',
})
export class ScheduleLockDeleteDialogComponent {
  scheduleLock?: IScheduleLock;

  constructor(
    protected scheduleLockService: ScheduleLockService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.scheduleLockService.delete(id).subscribe(() => {
      this.eventManager.broadcast('scheduleLockListModification');
      this.activeModal.close();
    });
  }
}
