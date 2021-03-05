import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IModuleConfiguration, ModuleConfiguration } from 'app/shared/model/module-configuration.model';
import { ModuleConfigurationService } from './module-configuration.service';

@Component({
  selector: 'jhi-module-configuration-update',
  templateUrl: './module-configuration-update.component.html',
})
export class ModuleConfigurationUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    mocName: [],
  });

  constructor(
    protected moduleConfigurationService: ModuleConfigurationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ moduleConfiguration }) => {
      this.updateForm(moduleConfiguration);
    });
  }

  updateForm(moduleConfiguration: IModuleConfiguration): void {
    this.editForm.patchValue({
      id: moduleConfiguration.id,
      mocName: moduleConfiguration.mocName,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const moduleConfiguration = this.createFromForm();
    if (moduleConfiguration.id !== undefined) {
      this.subscribeToSaveResponse(this.moduleConfigurationService.update(moduleConfiguration));
    } else {
      this.subscribeToSaveResponse(this.moduleConfigurationService.create(moduleConfiguration));
    }
  }

  private createFromForm(): IModuleConfiguration {
    return {
      ...new ModuleConfiguration(),
      id: this.editForm.get(['id'])!.value,
      mocName: this.editForm.get(['mocName'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IModuleConfiguration>>): void {
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
