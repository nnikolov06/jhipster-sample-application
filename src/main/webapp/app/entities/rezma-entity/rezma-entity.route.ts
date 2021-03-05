import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRezmaEntity, RezmaEntity } from 'app/shared/model/rezma-entity.model';
import { RezmaEntityService } from './rezma-entity.service';
import { RezmaEntityComponent } from './rezma-entity.component';
import { RezmaEntityDetailComponent } from './rezma-entity-detail.component';
import { RezmaEntityUpdateComponent } from './rezma-entity-update.component';

@Injectable({ providedIn: 'root' })
export class RezmaEntityResolve implements Resolve<IRezmaEntity> {
  constructor(private service: RezmaEntityService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRezmaEntity> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((rezmaEntity: HttpResponse<RezmaEntity>) => {
          if (rezmaEntity.body) {
            return of(rezmaEntity.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RezmaEntity());
  }
}

export const rezmaEntityRoute: Routes = [
  {
    path: '',
    component: RezmaEntityComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.rezmaEntity.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RezmaEntityDetailComponent,
    resolve: {
      rezmaEntity: RezmaEntityResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.rezmaEntity.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RezmaEntityUpdateComponent,
    resolve: {
      rezmaEntity: RezmaEntityResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.rezmaEntity.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RezmaEntityUpdateComponent,
    resolve: {
      rezmaEntity: RezmaEntityResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.rezmaEntity.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
