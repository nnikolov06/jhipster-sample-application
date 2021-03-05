import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IModuleConfiguration } from 'app/shared/model/module-configuration.model';
import { ModuleConfigurationService } from './module-configuration.service';

@Component({
  templateUrl: './module-configuration-delete-dialog.component.html',
})
export class ModuleConfigurationDeleteDialogComponent {
  moduleConfiguration?: IModuleConfiguration;

  constructor(
    protected moduleConfigurationService: ModuleConfigurationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.moduleConfigurationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('moduleConfigurationListModification');
      this.activeModal.close();
    });
  }
}
