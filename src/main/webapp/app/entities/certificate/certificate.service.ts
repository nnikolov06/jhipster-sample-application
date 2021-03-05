import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICertificate } from 'app/shared/model/certificate.model';

type EntityResponseType = HttpResponse<ICertificate>;
type EntityArrayResponseType = HttpResponse<ICertificate[]>;

@Injectable({ providedIn: 'root' })
export class CertificateService {
  public resourceUrl = SERVER_API_URL + 'api/certificates';

  constructor(protected http: HttpClient) {}

  create(certificate: ICertificate): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(certificate);
    return this.http
      .post<ICertificate>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(certificate: ICertificate): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(certificate);
    return this.http
      .put<ICertificate>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICertificate>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICertificate[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(certificate: ICertificate): ICertificate {
    const copy: ICertificate = Object.assign({}, certificate, {
      cerSysDate: certificate.cerSysDate && certificate.cerSysDate.isValid() ? certificate.cerSysDate.toJSON() : undefined,
      cerRequestDate:
        certificate.cerRequestDate && certificate.cerRequestDate.isValid() ? certificate.cerRequestDate.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.cerSysDate = res.body.cerSysDate ? moment(res.body.cerSysDate) : undefined;
      res.body.cerRequestDate = res.body.cerRequestDate ? moment(res.body.cerRequestDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((certificate: ICertificate) => {
        certificate.cerSysDate = certificate.cerSysDate ? moment(certificate.cerSysDate) : undefined;
        certificate.cerRequestDate = certificate.cerRequestDate ? moment(certificate.cerRequestDate) : undefined;
      });
    }
    return res;
  }
}
