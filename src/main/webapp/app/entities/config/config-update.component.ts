import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IConfig, Config } from 'app/shared/model/config.model';
import { ConfigService } from './config.service';
import { IModuleConfiguration } from 'app/shared/model/module-configuration.model';
import { ModuleConfigurationService } from 'app/entities/module-configuration/module-configuration.service';

@Component({
  selector: 'jhi-config-update',
  templateUrl: './config-update.component.html',
})
export class ConfigUpdateComponent implements OnInit {
  isSaving = false;
  moduleconfigurations: IModuleConfiguration[] = [];

  editForm = this.fb.group({
    id: [],
    cfgSection: [],
    cfgEnvironmnent: [],
    cfgItem: [],
    cfgValue: [],
    moduleConfiguration: [],
  });

  constructor(
    protected configService: ConfigService,
    protected moduleConfigurationService: ModuleConfigurationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ config }) => {
      this.updateForm(config);

      this.moduleConfigurationService
        .query()
        .subscribe((res: HttpResponse<IModuleConfiguration[]>) => (this.moduleconfigurations = res.body || []));
    });
  }

  updateForm(config: IConfig): void {
    this.editForm.patchValue({
      id: config.id,
      cfgSection: config.cfgSection,
      cfgEnvironmnent: config.cfgEnvironmnent,
      cfgItem: config.cfgItem,
      cfgValue: config.cfgValue,
      moduleConfiguration: config.moduleConfiguration,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const config = this.createFromForm();
    if (config.id !== undefined) {
      this.subscribeToSaveResponse(this.configService.update(config));
    } else {
      this.subscribeToSaveResponse(this.configService.create(config));
    }
  }

  private createFromForm(): IConfig {
    return {
      ...new Config(),
      id: this.editForm.get(['id'])!.value,
      cfgSection: this.editForm.get(['cfgSection'])!.value,
      cfgEnvironmnent: this.editForm.get(['cfgEnvironmnent'])!.value,
      cfgItem: this.editForm.get(['cfgItem'])!.value,
      cfgValue: this.editForm.get(['cfgValue'])!.value,
      moduleConfiguration: this.editForm.get(['moduleConfiguration'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IConfig>>): void {
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

  trackById(index: number, item: IModuleConfiguration): any {
    return item.id;
  }
}
