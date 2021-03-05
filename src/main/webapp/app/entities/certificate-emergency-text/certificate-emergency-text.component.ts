import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICertificateEmergencyText } from 'app/shared/model/certificate-emergency-text.model';
import { CertificateEmergencyTextService } from './certificate-emergency-text.service';
import { CertificateEmergencyTextDeleteDialogComponent } from './certificate-emergency-text-delete-dialog.component';

@Component({
  selector: 'jhi-certificate-emergency-text',
  templateUrl: './certificate-emergency-text.component.html',
})
export class CertificateEmergencyTextComponent implements OnInit, OnDestroy {
  certificateEmergencyTexts?: ICertificateEmergencyText[];
  eventSubscriber?: Subscription;

  constructor(
    protected certificateEmergencyTextService: CertificateEmergencyTextService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.certificateEmergencyTextService
      .query()
      .subscribe((res: HttpResponse<ICertificateEmergencyText[]>) => (this.certificateEmergencyTexts = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCertificateEmergencyTexts();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICertificateEmergencyText): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCertificateEmergencyTexts(): void {
    this.eventSubscriber = this.eventManager.subscribe('certificateEmergencyTextListModification', () => this.loadAll());
  }

  delete(certificateEmergencyText: ICertificateEmergencyText): void {
    const modalRef = this.modalService.open(CertificateEmergencyTextDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.certificateEmergencyText = certificateEmergencyText;
  }
}
