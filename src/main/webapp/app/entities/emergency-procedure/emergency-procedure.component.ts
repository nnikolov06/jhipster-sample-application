import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEmergencyProcedure } from 'app/shared/model/emergency-procedure.model';
import { EmergencyProcedureService } from './emergency-procedure.service';
import { EmergencyProcedureDeleteDialogComponent } from './emergency-procedure-delete-dialog.component';

@Component({
  selector: 'jhi-emergency-procedure',
  templateUrl: './emergency-procedure.component.html',
})
export class EmergencyProcedureComponent implements OnInit, OnDestroy {
  emergencyProcedures?: IEmergencyProcedure[];
  eventSubscriber?: Subscription;

  constructor(
    protected emergencyProcedureService: EmergencyProcedureService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.emergencyProcedureService
      .query()
      .subscribe((res: HttpResponse<IEmergencyProcedure[]>) => (this.emergencyProcedures = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEmergencyProcedures();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEmergencyProcedure): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEmergencyProcedures(): void {
    this.eventSubscriber = this.eventManager.subscribe('emergencyProcedureListModification', () => this.loadAll());
  }

  delete(emergencyProcedure: IEmergencyProcedure): void {
    const modalRef = this.modalService.open(EmergencyProcedureDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.emergencyProcedure = emergencyProcedure;
  }
}
