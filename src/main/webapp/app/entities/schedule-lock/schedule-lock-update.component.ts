import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IScheduleLock, ScheduleLock } from 'app/shared/model/schedule-lock.model';
import { ScheduleLockService } from './schedule-lock.service';

@Component({
  selector: 'jhi-schedule-lock-update',
  templateUrl: './schedule-lock-update.component.html',
})
export class ScheduleLockUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    slcName: [],
    slcLockUntil: [],
    slcLockedAt: [],
    slcLockedBy: [],
  });

  constructor(protected scheduleLockService: ScheduleLockService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ scheduleLock }) => {
      if (!scheduleLock.id) {
        const today = moment().startOf('day');
        scheduleLock.slcLockUntil = today;
        scheduleLock.slcLockedAt = today;
      }

      this.updateForm(scheduleLock);
    });
  }

  updateForm(scheduleLock: IScheduleLock): void {
    this.editForm.patchValue({
      id: scheduleLock.id,
      slcName: scheduleLock.slcName,
      slcLockUntil: scheduleLock.slcLockUntil ? scheduleLock.slcLockUntil.format(DATE_TIME_FORMAT) : null,
      slcLockedAt: scheduleLock.slcLockedAt ? scheduleLock.slcLockedAt.format(DATE_TIME_FORMAT) : null,
      slcLockedBy: scheduleLock.slcLockedBy,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const scheduleLock = this.createFromForm();
    if (scheduleLock.id !== undefined) {
      this.subscribeToSaveResponse(this.scheduleLockService.update(scheduleLock));
    } else {
      this.subscribeToSaveResponse(this.scheduleLockService.create(scheduleLock));
    }
  }

  private createFromForm(): IScheduleLock {
    return {
      ...new ScheduleLock(),
      id: this.editForm.get(['id'])!.value,
      slcName: this.editForm.get(['slcName'])!.value,
      slcLockUntil: this.editForm.get(['slcLockUntil'])!.value
        ? moment(this.editForm.get(['slcLockUntil'])!.value, DATE_TIME_FORMAT)
        : undefined,
      slcLockedAt: this.editForm.get(['slcLockedAt'])!.value
        ? moment(this.editForm.get(['slcLockedAt'])!.value, DATE_TIME_FORMAT)
        : undefined,
      slcLockedBy: this.editForm.get(['slcLockedBy'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IScheduleLock>>): void {
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
