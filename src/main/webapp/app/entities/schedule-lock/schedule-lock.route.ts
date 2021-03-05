import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IScheduleLock, ScheduleLock } from 'app/shared/model/schedule-lock.model';
import { ScheduleLockService } from './schedule-lock.service';
import { ScheduleLockComponent } from './schedule-lock.component';
import { ScheduleLockDetailComponent } from './schedule-lock-detail.component';
import { ScheduleLockUpdateComponent } from './schedule-lock-update.component';

@Injectable({ providedIn: 'root' })
export class ScheduleLockResolve implements Resolve<IScheduleLock> {
  constructor(private service: ScheduleLockService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IScheduleLock> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((scheduleLock: HttpResponse<ScheduleLock>) => {
          if (scheduleLock.body) {
            return of(scheduleLock.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ScheduleLock());
  }
}

export const scheduleLockRoute: Routes = [
  {
    path: '',
    component: ScheduleLockComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.scheduleLock.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ScheduleLockDetailComponent,
    resolve: {
      scheduleLock: ScheduleLockResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.scheduleLock.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ScheduleLockUpdateComponent,
    resolve: {
      scheduleLock: ScheduleLockResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.scheduleLock.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ScheduleLockUpdateComponent,
    resolve: {
      scheduleLock: ScheduleLockResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.scheduleLock.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
