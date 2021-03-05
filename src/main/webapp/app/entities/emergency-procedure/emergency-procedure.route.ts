import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEmergencyProcedure, EmergencyProcedure } from 'app/shared/model/emergency-procedure.model';
import { EmergencyProcedureService } from './emergency-procedure.service';
import { EmergencyProcedureComponent } from './emergency-procedure.component';
import { EmergencyProcedureDetailComponent } from './emergency-procedure-detail.component';
import { EmergencyProcedureUpdateComponent } from './emergency-procedure-update.component';

@Injectable({ providedIn: 'root' })
export class EmergencyProcedureResolve implements Resolve<IEmergencyProcedure> {
  constructor(private service: EmergencyProcedureService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEmergencyProcedure> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((emergencyProcedure: HttpResponse<EmergencyProcedure>) => {
          if (emergencyProcedure.body) {
            return of(emergencyProcedure.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EmergencyProcedure());
  }
}

export const emergencyProcedureRoute: Routes = [
  {
    path: '',
    component: EmergencyProcedureComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.emergencyProcedure.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EmergencyProcedureDetailComponent,
    resolve: {
      emergencyProcedure: EmergencyProcedureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.emergencyProcedure.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EmergencyProcedureUpdateComponent,
    resolve: {
      emergencyProcedure: EmergencyProcedureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.emergencyProcedure.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EmergencyProcedureUpdateComponent,
    resolve: {
      emergencyProcedure: EmergencyProcedureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.emergencyProcedure.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
