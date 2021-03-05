import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRezmaEntity, RezmaEntity } from 'app/shared/model/rezma-entity.model';
import { RezmaEntityService } from './rezma-entity.service';

@Component({
  selector: 'jhi-rezma-entity-update',
  templateUrl: './rezma-entity-update.component.html',
})
export class RezmaEntityUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    entName: [],
    entAddress: [],
    entSystemDate: [],
    entCreatedBy: [],
    entIdentifier: [],
    entType: [],
    entCountry: [],
    entIdentifierType: [],
    entTin: [],
    entDOB: [],
  });

  constructor(protected rezmaEntityService: RezmaEntityService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rezmaEntity }) => {
      if (!rezmaEntity.id) {
        const today = moment().startOf('day');
        rezmaEntity.entSystemDate = today;
      }

      this.updateForm(rezmaEntity);
    });
  }

  updateForm(rezmaEntity: IRezmaEntity): void {
    this.editForm.patchValue({
      id: rezmaEntity.id,
      entName: rezmaEntity.entName,
      entAddress: rezmaEntity.entAddress,
      entSystemDate: rezmaEntity.entSystemDate ? rezmaEntity.entSystemDate.format(DATE_TIME_FORMAT) : null,
      entCreatedBy: rezmaEntity.entCreatedBy,
      entIdentifier: rezmaEntity.entIdentifier,
      entType: rezmaEntity.entType,
      entCountry: rezmaEntity.entCountry,
      entIdentifierType: rezmaEntity.entIdentifierType,
      entTin: rezmaEntity.entTin,
      entDOB: rezmaEntity.entDOB,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const rezmaEntity = this.createFromForm();
    if (rezmaEntity.id !== undefined) {
      this.subscribeToSaveResponse(this.rezmaEntityService.update(rezmaEntity));
    } else {
      this.subscribeToSaveResponse(this.rezmaEntityService.create(rezmaEntity));
    }
  }

  private createFromForm(): IRezmaEntity {
    return {
      ...new RezmaEntity(),
      id: this.editForm.get(['id'])!.value,
      entName: this.editForm.get(['entName'])!.value,
      entAddress: this.editForm.get(['entAddress'])!.value,
      entSystemDate: this.editForm.get(['entSystemDate'])!.value
        ? moment(this.editForm.get(['entSystemDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      entCreatedBy: this.editForm.get(['entCreatedBy'])!.value,
      entIdentifier: this.editForm.get(['entIdentifier'])!.value,
      entType: this.editForm.get(['entType'])!.value,
      entCountry: this.editForm.get(['entCountry'])!.value,
      entIdentifierType: this.editForm.get(['entIdentifierType'])!.value,
      entTin: this.editForm.get(['entTin'])!.value,
      entDOB: this.editForm.get(['entDOB'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRezmaEntity>>): void {
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
