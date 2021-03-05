import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IModuleConfiguration } from 'app/shared/model/module-configuration.model';
import { ModuleConfigurationService } from './module-configuration.service';
import { ModuleConfigurationDeleteDialogComponent } from './module-configuration-delete-dialog.component';

@Component({
  selector: 'jhi-module-configuration',
  templateUrl: './module-configuration.component.html',
})
export class ModuleConfigurationComponent implements OnInit, OnDestroy {
  moduleConfigurations?: IModuleConfiguration[];
  eventSubscriber?: Subscription;

  constructor(
    protected moduleConfigurationService: ModuleConfigurationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.moduleConfigurationService
      .query()
      .subscribe((res: HttpResponse<IModuleConfiguration[]>) => (this.moduleConfigurations = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInModuleConfigurations();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IModuleConfiguration): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInModuleConfigurations(): void {
    this.eventSubscriber = this.eventManager.subscribe('moduleConfigurationListModification', () => this.loadAll());
  }

  delete(moduleConfiguration: IModuleConfiguration): void {
    const modalRef = this.modalService.open(ModuleConfigurationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.moduleConfiguration = moduleConfiguration;
  }
}
