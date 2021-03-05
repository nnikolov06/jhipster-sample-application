import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IDocument, Document } from 'app/shared/model/document.model';
import { DocumentService } from './document.service';

@Component({
  selector: 'jhi-document-update',
  templateUrl: './document-update.component.html',
})
export class DocumentUpdateComponent implements OnInit {
  isSaving = false;
  docDateDp: any;
  docIntoForceDateDp: any;
  docDeferredDateDp: any;
  docRescheduledDateDp: any;

  editForm = this.fb.group({
    id: [],
    docNumber: [],
    docDate: [],
    docDescription: [],
    docType: [],
    docSysDate: [],
    docCreatedBy: [],
    docMrn: [],
    docStatus: [],
    docLastChangeDate: [],
    docIntoForce: [],
    docDeferred: [],
    docRescheduled: [],
    docIntoForceDate: [],
    docDeferredDate: [],
    docRescheduledDate: [],
  });

  constructor(protected documentService: DocumentService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ document }) => {
      if (!document.id) {
        const today = moment().startOf('day');
        document.docSysDate = today;
        document.docLastChangeDate = today;
      }

      this.updateForm(document);
    });
  }

  updateForm(document: IDocument): void {
    this.editForm.patchValue({
      id: document.id,
      docNumber: document.docNumber,
      docDate: document.docDate,
      docDescription: document.docDescription,
      docType: document.docType,
      docSysDate: document.docSysDate ? document.docSysDate.format(DATE_TIME_FORMAT) : null,
      docCreatedBy: document.docCreatedBy,
      docMrn: document.docMrn,
      docStatus: document.docStatus,
      docLastChangeDate: document.docLastChangeDate ? document.docLastChangeDate.format(DATE_TIME_FORMAT) : null,
      docIntoForce: document.docIntoForce,
      docDeferred: document.docDeferred,
      docRescheduled: document.docRescheduled,
      docIntoForceDate: document.docIntoForceDate,
      docDeferredDate: document.docDeferredDate,
      docRescheduledDate: document.docRescheduledDate,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const document = this.createFromForm();
    if (document.id !== undefined) {
      this.subscribeToSaveResponse(this.documentService.update(document));
    } else {
      this.subscribeToSaveResponse(this.documentService.create(document));
    }
  }

  private createFromForm(): IDocument {
    return {
      ...new Document(),
      id: this.editForm.get(['id'])!.value,
      docNumber: this.editForm.get(['docNumber'])!.value,
      docDate: this.editForm.get(['docDate'])!.value,
      docDescription: this.editForm.get(['docDescription'])!.value,
      docType: this.editForm.get(['docType'])!.value,
      docSysDate: this.editForm.get(['docSysDate'])!.value ? moment(this.editForm.get(['docSysDate'])!.value, DATE_TIME_FORMAT) : undefined,
      docCreatedBy: this.editForm.get(['docCreatedBy'])!.value,
      docMrn: this.editForm.get(['docMrn'])!.value,
      docStatus: this.editForm.get(['docStatus'])!.value,
      docLastChangeDate: this.editForm.get(['docLastChangeDate'])!.value
        ? moment(this.editForm.get(['docLastChangeDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      docIntoForce: this.editForm.get(['docIntoForce'])!.value,
      docDeferred: this.editForm.get(['docDeferred'])!.value,
      docRescheduled: this.editForm.get(['docRescheduled'])!.value,
      docIntoForceDate: this.editForm.get(['docIntoForceDate'])!.value,
      docDeferredDate: this.editForm.get(['docDeferredDate'])!.value,
      docRescheduledDate: this.editForm.get(['docRescheduledDate'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDocument>>): void {
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
}
