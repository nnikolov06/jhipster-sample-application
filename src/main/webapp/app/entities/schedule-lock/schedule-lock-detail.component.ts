import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IScheduleLock } from 'app/shared/model/schedule-lock.model';

@Component({
  selector: 'jhi-schedule-lock-detail',
  templateUrl: './schedule-lock-detail.component.html',
})
export class ScheduleLockDetailComponent implements OnInit {
  scheduleLock: IScheduleLock | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ scheduleLock }) => (this.scheduleLock = scheduleLock));
  }

  previousState(): void {
    window.history.back();
  }
}
