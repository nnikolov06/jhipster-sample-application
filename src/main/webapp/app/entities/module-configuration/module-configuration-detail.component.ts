import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IModuleConfiguration } from 'app/shared/model/module-configuration.model';

@Component({
  selector: 'jhi-module-configuration-detail',
  templateUrl: './module-configuration-detail.component.html',
})
export class ModuleConfigurationDetailComponent implements OnInit {
  moduleConfiguration: IModuleConfiguration | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ moduleConfiguration }) => (this.moduleConfiguration = moduleConfiguration));
  }

  previousState(): void {
    window.history.back();
  }
}
