import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IScheduleLock } from 'app/shared/model/schedule-lock.model';
import { ScheduleLockService } from './schedule-lock.service';
import { ScheduleLockDeleteDialogComponent } from './schedule-lock-delete-dialog.component';

@Component({
  selector: 'jhi-schedule-lock',
  templateUrl: './schedule-lock.component.html',
})
export class ScheduleLockComponent implements OnInit, OnDestroy {
  scheduleLocks?: IScheduleLock[];
  eventSubscriber?: Subscription;

  constructor(
    protected scheduleLockService: ScheduleLockService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.scheduleLockService.query().subscribe((res: HttpResponse<IScheduleLock[]>) => (this.scheduleLocks = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInScheduleLocks();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IScheduleLock): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInScheduleLocks(): void {
    this.eventSubscriber = this.eventManager.subscribe('scheduleLockListModification', () => this.loadAll());
  }

  delete(scheduleLock: IScheduleLock): void {
    const modalRef = this.modalService.open(ScheduleLockDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.scheduleLock = scheduleLock;
  }
}
