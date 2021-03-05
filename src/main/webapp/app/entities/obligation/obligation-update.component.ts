import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IObligation, Obligation } from 'app/shared/model/obligation.model';
import { ObligationService } from './obligation.service';
import { IDocument } from 'app/shared/model/document.model';
import { DocumentService } from 'app/entities/document/document.service';
import { IRezmaEntity } from 'app/shared/model/rezma-entity.model';
import { RezmaEntityService } from 'app/entities/rezma-entity/rezma-entity.service';

type SelectableEntity = IDocument | IRezmaEntity;

@Component({
  selector: 'jhi-obligation-update',
  templateUrl: './obligation-update.component.html',
})
export class ObligationUpdateComponent implements OnInit {
  isSaving = false;
  documents: IDocument[] = [];
  rezmaentities: IRezmaEntity[] = [];

  editForm = this.fb.group({
    id: [],
    oblSysDate: [],
    oblCreatedBy: [],
    oblCustomsOffice: [],
    oblType: [],
    oblAmount: [],
    oblLapsed: [],
    oblVpoNumber: [],
    oblTransactionNumber: [],
    oblMraDate: [],
    oblState: [],
    oblIdentifier: [],
    oblAmountDifference: [],
    oblGuarantyDifference: [],
    oblCustomsOfficeName: [],
    document: [],
    rezmaEntities: [],
  });

  constructor(
    protected obligationService: ObligationService,
    protected documentService: DocumentService,
    protected rezmaEntityService: RezmaEntityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ obligation }) => {
      if (!obligation.id) {
        const today = moment().startOf('day');
        obligation.oblSysDate = today;
        obligation.oblMraDate = today;
      }

      this.updateForm(obligation);

      this.documentService.query().subscribe((res: HttpResponse<IDocument[]>) => (this.documents = res.body || []));

      this.rezmaEntityService.query().subscribe((res: HttpResponse<IRezmaEntity[]>) => (this.rezmaentities = res.body || []));
    });
  }

  updateForm(obligation: IObligation): void {
    this.editForm.patchValue({
      id: obligation.id,
      oblSysDate: obligation.oblSysDate ? obligation.oblSysDate.format(DATE_TIME_FORMAT) : null,
      oblCreatedBy: obligation.oblCreatedBy,
      oblCustomsOffice: obligation.oblCustomsOffice,
      oblType: obligation.oblType,
      oblAmount: obligation.oblAmount,
      oblLapsed: obligation.oblLapsed,
      oblVpoNumber: obligation.oblVpoNumber,
      oblTransactionNumber: obligation.oblTransactionNumber,
      oblMraDate: obligation.oblMraDate ? obligation.oblMraDate.format(DATE_TIME_FORMAT) : null,
      oblState: obligation.oblState,
      oblIdentifier: obligation.oblIdentifier,
      oblAmountDifference: obligation.oblAmountDifference,
      oblGuarantyDifference: obligation.oblGuarantyDifference,
      oblCustomsOfficeName: obligation.oblCustomsOfficeName,
      document: obligation.document,
      rezmaEntities: obligation.rezmaEntities,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const obligation = this.createFromForm();
    if (obligation.id !== undefined) {
      this.subscribeToSaveResponse(this.obligationService.update(obligation));
    } else {
      this.subscribeToSaveResponse(this.obligationService.create(obligation));
    }
  }

  private createFromForm(): IObligation {
    return {
      ...new Obligation(),
      id: this.editForm.get(['id'])!.value,
      oblSysDate: this.editForm.get(['oblSysDate'])!.value ? moment(this.editForm.get(['oblSysDate'])!.value, DATE_TIME_FORMAT) : undefined,
      oblCreatedBy: this.editForm.get(['oblCreatedBy'])!.value,
      oblCustomsOffice: this.editForm.get(['oblCustomsOffice'])!.value,
      oblType: this.editForm.get(['oblType'])!.value,
      oblAmount: this.editForm.get(['oblAmount'])!.value,
      oblLapsed: this.editForm.get(['oblLapsed'])!.value,
      oblVpoNumber: this.editForm.get(['oblVpoNumber'])!.value,
      oblTransactionNumber: this.editForm.get(['oblTransactionNumber'])!.value,
      oblMraDate: this.editForm.get(['oblMraDate'])!.value ? moment(this.editForm.get(['oblMraDate'])!.value, DATE_TIME_FORMAT) : undefined,
      oblState: this.editForm.get(['oblState'])!.value,
      oblIdentifier: this.editForm.get(['oblIdentifier'])!.value,
      oblAmountDifference: this.editForm.get(['oblAmountDifference'])!.value,
      oblGuarantyDifference: this.editForm.get(['oblGuarantyDifference'])!.value,
      oblCustomsOfficeName: this.editForm.get(['oblCustomsOfficeName'])!.value,
      document: this.editForm.get(['document'])!.value,
      rezmaEntities: this.editForm.get(['rezmaEntities'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IObligation>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }

  getSelected(selectedVals: IRezmaEntity[], option: IRezmaEntity): IRezmaEntity {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
