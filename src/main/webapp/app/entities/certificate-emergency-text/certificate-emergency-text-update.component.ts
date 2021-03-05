import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICertificateEmergencyText, CertificateEmergencyText } from 'app/shared/model/certificate-emergency-text.model';
import { CertificateEmergencyTextService } from './certificate-emergency-text.service';

@Component({
  selector: 'jhi-certificate-emergency-text-update',
  templateUrl: './certificate-emergency-text-update.component.html',
})
export class CertificateEmergencyTextUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    cetText: [],
    cetIsActive: [],
  });

  constructor(
    protected certificateEmergencyTextService: CertificateEmergencyTextService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ certificateEmergencyText }) => {
      this.updateForm(certificateEmergencyText);
    });
  }

  updateForm(certificateEmergencyText: ICertificateEmergencyText): void {
    this.editForm.patchValue({
      id: certificateEmergencyText.id,
      cetText: certificateEmergencyText.cetText,
      cetIsActive: certificateEmergencyText.cetIsActive,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const certificateEmergencyText = this.createFromForm();
    if (certificateEmergencyText.id !== undefined) {
      this.subscribeToSaveResponse(this.certificateEmergencyTextService.update(certificateEmergencyText));
    } else {
      this.subscribeToSaveResponse(this.certificateEmergencyTextService.create(certificateEmergencyText));
    }
  }

  private createFromForm(): ICertificateEmergencyText {
    return {
      ...new CertificateEmergencyText(),
      id: this.editForm.get(['id'])!.value,
      cetText: this.editForm.get(['cetText'])!.value,
      cetIsActive: this.editForm.get(['cetIsActive'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICertificateEmergencyText>>): void {
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
