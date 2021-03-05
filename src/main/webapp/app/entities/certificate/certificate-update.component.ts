import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICertificate, Certificate } from 'app/shared/model/certificate.model';
import { CertificateService } from './certificate.service';
import { IRezmaEntity } from 'app/shared/model/rezma-entity.model';
import { RezmaEntityService } from 'app/entities/rezma-entity/rezma-entity.service';

@Component({
  selector: 'jhi-certificate-update',
  templateUrl: './certificate-update.component.html',
})
export class CertificateUpdateComponent implements OnInit {
  isSaving = false;
  rezmaentities: IRezmaEntity[] = [];
  cerRequestDateDp: any;

  editForm = this.fb.group({
    id: [],
    cerNumber: [],
    cerDate: [],
    cerSysDate: [],
    cerCreatedBy: [],
    cerFileName: [],
    cerServiceApplicant: [],
    cerSigningPerson: [],
    cerSigningPersonPosition: [],
    cerCertificateType: [],
    cerCheckBoxValues: [],
    cerRequestNumber: [],
    cerRequestDate: [],
    cerIsDeactivated: [],
    cerState: [],
    cerHasObligation: [],
    cerDocumentState: [],
    rezmaEntity: [],
  });

  constructor(
    protected certificateService: CertificateService,
    protected rezmaEntityService: RezmaEntityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ certificate }) => {
      if (!certificate.id) {
        const today = moment().startOf('day');
        certificate.cerSysDate = today;
      }

      this.updateForm(certificate);

      this.rezmaEntityService.query().subscribe((res: HttpResponse<IRezmaEntity[]>) => (this.rezmaentities = res.body || []));
    });
  }

  updateForm(certificate: ICertificate): void {
    this.editForm.patchValue({
      id: certificate.id,
      cerNumber: certificate.cerNumber,
      cerDate: certificate.cerDate,
      cerSysDate: certificate.cerSysDate ? certificate.cerSysDate.format(DATE_TIME_FORMAT) : null,
      cerCreatedBy: certificate.cerCreatedBy,
      cerFileName: certificate.cerFileName,
      cerServiceApplicant: certificate.cerServiceApplicant,
      cerSigningPerson: certificate.cerSigningPerson,
      cerSigningPersonPosition: certificate.cerSigningPersonPosition,
      cerCertificateType: certificate.cerCertificateType,
      cerCheckBoxValues: certificate.cerCheckBoxValues,
      cerRequestNumber: certificate.cerRequestNumber,
      cerRequestDate: certificate.cerRequestDate,
      cerIsDeactivated: certificate.cerIsDeactivated,
      cerState: certificate.cerState,
      cerHasObligation: certificate.cerHasObligation,
      cerDocumentState: certificate.cerDocumentState,
      rezmaEntity: certificate.rezmaEntity,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const certificate = this.createFromForm();
    if (certificate.id !== undefined) {
      this.subscribeToSaveResponse(this.certificateService.update(certificate));
    } else {
      this.subscribeToSaveResponse(this.certificateService.create(certificate));
    }
  }

  private createFromForm(): ICertificate {
    return {
      ...new Certificate(),
      id: this.editForm.get(['id'])!.value,
      cerNumber: this.editForm.get(['cerNumber'])!.value,
      cerDate: this.editForm.get(['cerDate'])!.value,
      cerSysDate: this.editForm.get(['cerSysDate'])!.value ? moment(this.editForm.get(['cerSysDate'])!.value, DATE_TIME_FORMAT) : undefined,
      cerCreatedBy: this.editForm.get(['cerCreatedBy'])!.value,
      cerFileName: this.editForm.get(['cerFileName'])!.value,
      cerServiceApplicant: this.editForm.get(['cerServiceApplicant'])!.value,
      cerSigningPerson: this.editForm.get(['cerSigningPerson'])!.value,
      cerSigningPersonPosition: this.editForm.get(['cerSigningPersonPosition'])!.value,
      cerCertificateType: this.editForm.get(['cerCertificateType'])!.value,
      cerCheckBoxValues: this.editForm.get(['cerCheckBoxValues'])!.value,
      cerRequestNumber: this.editForm.get(['cerRequestNumber'])!.value,
      cerRequestDate: this.editForm.get(['cerRequestDate'])!.value,
      cerIsDeactivated: this.editForm.get(['cerIsDeactivated'])!.value,
      cerState: this.editForm.get(['cerState'])!.value,
      cerHasObligation: this.editForm.get(['cerHasObligation'])!.value,
      cerDocumentState: this.editForm.get(['cerDocumentState'])!.value,
      rezmaEntity: this.editForm.get(['rezmaEntity'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICertificate>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IRezmaEntity): any {
    return item.id;
  }
}
