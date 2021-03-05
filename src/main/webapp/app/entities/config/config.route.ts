import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IConfig, Config } from 'app/shared/model/config.model';
import { ConfigService } from './config.service';
import { ConfigComponent } from './config.component';
import { ConfigDetailComponent } from './config-detail.component';
import { ConfigUpdateComponent } from './config-update.component';

@Injectable({ providedIn: 'root' })
export class ConfigResolve implements Resolve<IConfig> {
  constructor(private service: ConfigService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IConfig> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((config: HttpResponse<Config>) => {
          if (config.body) {
            return of(config.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Config());
  }
}

export const configRoute: Routes = [
  {
    path: '',
    component: ConfigComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.config.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ConfigDetailComponent,
    resolve: {
      config: ConfigResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.config.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ConfigUpdateComponent,
    resolve: {
      config: ConfigResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.config.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ConfigUpdateComponent,
    resolve: {
      config: ConfigResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.config.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
