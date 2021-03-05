import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRezmaEntity } from 'app/shared/model/rezma-entity.model';

@Component({
  selector: 'jhi-rezma-entity-detail',
  templateUrl: './rezma-entity-detail.component.html',
})
export class RezmaEntityDetailComponent implements OnInit {
  rezmaEntity: IRezmaEntity | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rezmaEntity }) => (this.rezmaEntity = rezmaEntity));
  }

  previousState(): void {
    window.history.back();
  }
}
