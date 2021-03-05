import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IObligation } from 'app/shared/model/obligation.model';

@Component({
  selector: 'jhi-obligation-detail',
  templateUrl: './obligation-detail.component.html',
})
export class ObligationDetailComponent implements OnInit {
  obligation: IObligation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ obligation }) => (this.obligation = obligation));
  }

  previousState(): void {
    window.history.back();
  }
}
