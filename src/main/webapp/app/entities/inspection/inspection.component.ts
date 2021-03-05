import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IInspection } from 'app/shared/model/inspection.model';
import { InspectionService } from './inspection.service';
import { InspectionDeleteDialogComponent } from './inspection-delete-dialog.component';

@Component({
  selector: 'jhi-inspection',
  templateUrl: './inspection.component.html',
})
export class InspectionComponent implements OnInit, OnDestroy {
  inspections?: IInspection[];
  eventSubscriber?: Subscription;

  constructor(protected inspectionService: InspectionService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.inspectionService.query().subscribe((res: HttpResponse<IInspection[]>) => (this.inspections = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInInspections();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IInspection): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInInspections(): void {
    this.eventSubscriber = this.eventManager.subscribe('inspectionListModification', () => this.loadAll());
  }

  delete(inspection: IInspection): void {
    const modalRef = this.modalService.open(InspectionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.inspection = inspection;
  }
}
