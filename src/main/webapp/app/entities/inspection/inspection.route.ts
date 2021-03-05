import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInspection, Inspection } from 'app/shared/model/inspection.model';
import { InspectionService } from './inspection.service';
import { InspectionComponent } from './inspection.component';
import { InspectionDetailComponent } from './inspection-detail.component';
import { InspectionUpdateComponent } from './inspection-update.component';

@Injectable({ providedIn: 'root' })
export class InspectionResolve implements Resolve<IInspection> {
  constructor(private service: InspectionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInspection> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((inspection: HttpResponse<Inspection>) => {
          if (inspection.body) {
            return of(inspection.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Inspection());
  }
}

export const inspectionRoute: Routes = [
  {
    path: '',
    component: InspectionComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.inspection.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: InspectionDetailComponent,
    resolve: {
      inspection: InspectionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.inspection.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: InspectionUpdateComponent,
    resolve: {
      inspection: InspectionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.inspection.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: InspectionUpdateComponent,
    resolve: {
      inspection: InspectionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.inspection.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
