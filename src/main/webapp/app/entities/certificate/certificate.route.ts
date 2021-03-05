import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICertificate, Certificate } from 'app/shared/model/certificate.model';
import { CertificateService } from './certificate.service';
import { CertificateComponent } from './certificate.component';
import { CertificateDetailComponent } from './certificate-detail.component';
import { CertificateUpdateComponent } from './certificate-update.component';

@Injectable({ providedIn: 'root' })
export class CertificateResolve implements Resolve<ICertificate> {
  constructor(private service: CertificateService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICertificate> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((certificate: HttpResponse<Certificate>) => {
          if (certificate.body) {
            return of(certificate.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Certificate());
  }
}

export const certificateRoute: Routes = [
  {
    path: '',
    component: CertificateComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.certificate.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CertificateDetailComponent,
    resolve: {
      certificate: CertificateResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.certificate.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CertificateUpdateComponent,
    resolve: {
      certificate: CertificateResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.certificate.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CertificateUpdateComponent,
    resolve: {
      certificate: CertificateResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.certificate.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
