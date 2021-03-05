import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IConfig } from 'app/shared/model/config.model';
import { ConfigService } from './config.service';

@Component({
  templateUrl: './config-delete-dialog.component.html',
})
export class ConfigDeleteDialogComponent {
  config?: IConfig;

  constructor(protected configService: ConfigService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.configService.delete(id).subscribe(() => {
      this.eventManager.broadcast('configListModification');
      this.activeModal.close();
    });
  }
}
