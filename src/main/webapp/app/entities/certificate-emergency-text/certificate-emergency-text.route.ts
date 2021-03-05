import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICertificateEmergencyText, CertificateEmergencyText } from 'app/shared/model/certificate-emergency-text.model';
import { CertificateEmergencyTextService } from './certificate-emergency-text.service';
import { CertificateEmergencyTextComponent } from './certificate-emergency-text.component';
import { CertificateEmergencyTextDetailComponent } from './certificate-emergency-text-detail.component';
import { CertificateEmergencyTextUpdateComponent } from './certificate-emergency-text-update.component';

@Injectable({ providedIn: 'root' })
export class CertificateEmergencyTextResolve implements Resolve<ICertificateEmergencyText> {
  constructor(private service: CertificateEmergencyTextService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICertificateEmergencyText> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((certificateEmergencyText: HttpResponse<CertificateEmergencyText>) => {
          if (certificateEmergencyText.body) {
            return of(certificateEmergencyText.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CertificateEmergencyText());
  }
}

export const certificateEmergencyTextRoute: Routes = [
  {
    path: '',
    component: CertificateEmergencyTextComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.certificateEmergencyText.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CertificateEmergencyTextDetailComponent,
    resolve: {
      certificateEmergencyText: CertificateEmergencyTextResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.certificateEmergencyText.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CertificateEmergencyTextUpdateComponent,
    resolve: {
      certificateEmergencyText: CertificateEmergencyTextResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.certificateEmergencyText.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CertificateEmergencyTextUpdateComponent,
    resolve: {
      certificateEmergencyText: CertificateEmergencyTextResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.certificateEmergencyText.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
