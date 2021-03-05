import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IEmergencyProcedure, EmergencyProcedure } from 'app/shared/model/emergency-procedure.model';
import { EmergencyProcedureService } from './emergency-procedure.service';

@Component({
  selector: 'jhi-emergency-procedure-update',
  templateUrl: './emergency-procedure-update.component.html',
})
export class EmergencyProcedureUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    empSystem: [],
    empReason: [],
    empUser: [],
    empIsActive: [],
    empDateStart: [],
    empDateEnd: [],
  });

  constructor(
    protected emergencyProcedureService: EmergencyProcedureService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ emergencyProcedure }) => {
      if (!emergencyProcedure.id) {
        const today = moment().startOf('day');
        emergencyProcedure.empDateStart = today;
        emergencyProcedure.empDateEnd = today;
      }

      this.updateForm(emergencyProcedure);
    });
  }

  updateForm(emergencyProcedure: IEmergencyProcedure): void {
    this.editForm.patchValue({
      id: emergencyProcedure.id,
      empSystem: emergencyProcedure.empSystem,
      empReason: emergencyProcedure.empReason,
      empUser: emergencyProcedure.empUser,
      empIsActive: emergencyProcedure.empIsActive,
      empDateStart: emergencyProcedure.empDateStart ? emergencyProcedure.empDateStart.format(DATE_TIME_FORMAT) : null,
      empDateEnd: emergencyProcedure.empDateEnd ? emergencyProcedure.empDateEnd.format(DATE_TIME_FORMAT) : null,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const emergencyProcedure = this.createFromForm();
    if (emergencyProcedure.id !== undefined) {
      this.subscribeToSaveResponse(this.emergencyProcedureService.update(emergencyProcedure));
    } else {
      this.subscribeToSaveResponse(this.emergencyProcedureService.create(emergencyProcedure));
    }
  }

  private createFromForm(): IEmergencyProcedure {
    return {
      ...new EmergencyProcedure(),
      id: this.editForm.get(['id'])!.value,
      empSystem: this.editForm.get(['empSystem'])!.value,
      empReason: this.editForm.get(['empReason'])!.value,
      empUser: this.editForm.get(['empUser'])!.value,
      empIsActive: this.editForm.get(['empIsActive'])!.value,
      empDateStart: this.editForm.get(['empDateStart'])!.value
        ? moment(this.editForm.get(['empDateStart'])!.value, DATE_TIME_FORMAT)
        : undefined,
      empDateEnd: this.editForm.get(['empDateEnd'])!.value ? moment(this.editForm.get(['empDateEnd'])!.value, DATE_TIME_FORMAT) : undefined,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmergencyProcedure>>): void {
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
