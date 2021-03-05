import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IModuleConfiguration, ModuleConfiguration } from 'app/shared/model/module-configuration.model';
import { ModuleConfigurationService } from './module-configuration.service';
import { ModuleConfigurationComponent } from './module-configuration.component';
import { ModuleConfigurationDetailComponent } from './module-configuration-detail.component';
import { ModuleConfigurationUpdateComponent } from './module-configuration-update.component';

@Injectable({ providedIn: 'root' })
export class ModuleConfigurationResolve implements Resolve<IModuleConfiguration> {
  constructor(private service: ModuleConfigurationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IModuleConfiguration> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((moduleConfiguration: HttpResponse<ModuleConfiguration>) => {
          if (moduleConfiguration.body) {
            return of(moduleConfiguration.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ModuleConfiguration());
  }
}

export const moduleConfigurationRoute: Routes = [
  {
    path: '',
    component: ModuleConfigurationComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.moduleConfiguration.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ModuleConfigurationDetailComponent,
    resolve: {
      moduleConfiguration: ModuleConfigurationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.moduleConfiguration.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ModuleConfigurationUpdateComponent,
    resolve: {
      moduleConfiguration: ModuleConfigurationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.moduleConfiguration.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ModuleConfigurationUpdateComponent,
    resolve: {
      moduleConfiguration: ModuleConfigurationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.moduleConfiguration.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
