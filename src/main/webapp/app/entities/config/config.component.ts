import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IConfig } from 'app/shared/model/config.model';
import { ConfigService } from './config.service';
import { ConfigDeleteDialogComponent } from './config-delete-dialog.component';

@Component({
  selector: 'jhi-config',
  templateUrl: './config.component.html',
})
export class ConfigComponent implements OnInit, OnDestroy {
  configs?: IConfig[];
  eventSubscriber?: Subscription;

  constructor(protected configService: ConfigService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.configService.query().subscribe((res: HttpResponse<IConfig[]>) => (this.configs = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInConfigs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IConfig): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInConfigs(): void {
    this.eventSubscriber = this.eventManager.subscribe('configListModification', () => this.loadAll());
  }

  delete(config: IConfig): void {
    const modalRef = this.modalService.open(ConfigDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.config = config;
  }
}
