import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IInspection, Inspection } from 'app/shared/model/inspection.model';
import { InspectionService } from './inspection.service';
import { IRezmaEntity } from 'app/shared/model/rezma-entity.model';
import { RezmaEntityService } from 'app/entities/rezma-entity/rezma-entity.service';

@Component({
  selector: 'jhi-inspection-update',
  templateUrl: './inspection-update.component.html',
})
export class InspectionUpdateComponent implements OnInit {
  isSaving = false;
  rezmaentities: IRezmaEntity[] = [];

  editForm = this.fb.group({
    id: [],
    insNumber: [],
    insDate: [],
    insCustomsOffice: [],
    insUser: [],
    insSysDate: [],
    insType: [],
    rezmaEntities: [],
  });

  constructor(
    protected inspectionService: InspectionService,
    protected rezmaEntityService: RezmaEntityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ inspection }) => {
      if (!inspection.id) {
        const today = moment().startOf('day');
        inspection.insDate = today;
        inspection.insSysDate = today;
      }

      this.updateForm(inspection);

      this.rezmaEntityService.query().subscribe((res: HttpResponse<IRezmaEntity[]>) => (this.rezmaentities = res.body || []));
    });
  }

  updateForm(inspection: IInspection): void {
    this.editForm.patchValue({
      id: inspection.id,
      insNumber: inspection.insNumber,
      insDate: inspection.insDate ? inspection.insDate.format(DATE_TIME_FORMAT) : null,
      insCustomsOffice: inspection.insCustomsOffice,
      insUser: inspection.insUser,
      insSysDate: inspection.insSysDate ? inspection.insSysDate.format(DATE_TIME_FORMAT) : null,
      insType: inspection.insType,
      rezmaEntities: inspection.rezmaEntities,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const inspection = this.createFromForm();
    if (inspection.id !== undefined) {
      this.subscribeToSaveResponse(this.inspectionService.update(inspection));
    } else {
      this.subscribeToSaveResponse(this.inspectionService.create(inspection));
    }
  }

  private createFromForm(): IInspection {
    return {
      ...new Inspection(),
      id: this.editForm.get(['id'])!.value,
      insNumber: this.editForm.get(['insNumber'])!.value,
      insDate: this.editForm.get(['insDate'])!.value ? moment(this.editForm.get(['insDate'])!.value, DATE_TIME_FORMAT) : undefined,
      insCustomsOffice: this.editForm.get(['insCustomsOffice'])!.value,
      insUser: this.editForm.get(['insUser'])!.value,
      insSysDate: this.editForm.get(['insSysDate'])!.value ? moment(this.editForm.get(['insSysDate'])!.value, DATE_TIME_FORMAT) : undefined,
      insType: this.editForm.get(['insType'])!.value,
      rezmaEntities: this.editForm.get(['rezmaEntities'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInspection>>): void {
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
