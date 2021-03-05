import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IObligation } from 'app/shared/model/obligation.model';
import { ObligationService } from './obligation.service';
import { ObligationDeleteDialogComponent } from './obligation-delete-dialog.component';

@Component({
  selector: 'jhi-obligation',
  templateUrl: './obligation.component.html',
})
export class ObligationComponent implements OnInit, OnDestroy {
  obligations?: IObligation[];
  eventSubscriber?: Subscription;

  constructor(protected obligationService: ObligationService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.obligationService.query().subscribe((res: HttpResponse<IObligation[]>) => (this.obligations = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInObligations();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IObligation): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInObligations(): void {
    this.eventSubscriber = this.eventManager.subscribe('obligationListModification', () => this.loadAll());
  }

  delete(obligation: IObligation): void {
    const modalRef = this.modalService.open(ObligationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.obligation = obligation;
  }
}
