import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRezmaEntity } from 'app/shared/model/rezma-entity.model';
import { RezmaEntityService } from './rezma-entity.service';
import { RezmaEntityDeleteDialogComponent } from './rezma-entity-delete-dialog.component';

@Component({
  selector: 'jhi-rezma-entity',
  templateUrl: './rezma-entity.component.html',
})
export class RezmaEntityComponent implements OnInit, OnDestroy {
  rezmaEntities?: IRezmaEntity[];
  eventSubscriber?: Subscription;

  constructor(
    protected rezmaEntityService: RezmaEntityService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.rezmaEntityService.query().subscribe((res: HttpResponse<IRezmaEntity[]>) => (this.rezmaEntities = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInRezmaEntities();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRezmaEntity): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRezmaEntities(): void {
    this.eventSubscriber = this.eventManager.subscribe('rezmaEntityListModification', () => this.loadAll());
  }

  delete(rezmaEntity: IRezmaEntity): void {
    const modalRef = this.modalService.open(RezmaEntityDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.rezmaEntity = rezmaEntity;
  }
}
