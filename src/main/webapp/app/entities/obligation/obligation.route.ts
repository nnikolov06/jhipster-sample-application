import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IObligation, Obligation } from 'app/shared/model/obligation.model';
import { ObligationService } from './obligation.service';
import { ObligationComponent } from './obligation.component';
import { ObligationDetailComponent } from './obligation-detail.component';
import { ObligationUpdateComponent } from './obligation-update.component';

@Injectable({ providedIn: 'root' })
export class ObligationResolve implements Resolve<IObligation> {
  constructor(private service: ObligationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IObligation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((obligation: HttpResponse<Obligation>) => {
          if (obligation.body) {
            return of(obligation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Obligation());
  }
}

export const obligationRoute: Routes = [
  {
    path: '',
    component: ObligationComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.obligation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ObligationDetailComponent,
    resolve: {
      obligation: ObligationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.obligation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ObligationUpdateComponent,
    resolve: {
      obligation: ObligationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.obligation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ObligationUpdateComponent,
    resolve: {
      obligation: ObligationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.obligation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
