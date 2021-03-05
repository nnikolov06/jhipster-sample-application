import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEmergencyProcedure } from 'app/shared/model/emergency-procedure.model';

@Component({
  selector: 'jhi-emergency-procedure-detail',
  templateUrl: './emergency-procedure-detail.component.html',
})
export class EmergencyProcedureDetailComponent implements OnInit {
  emergencyProcedure: IEmergencyProcedure | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ emergencyProcedure }) => (this.emergencyProcedure = emergencyProcedure));
  }

  previousState(): void {
    window.history.back();
  }
}
