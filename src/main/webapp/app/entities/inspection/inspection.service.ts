import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IInspection } from 'app/shared/model/inspection.model';

type EntityResponseType = HttpResponse<IInspection>;
type EntityArrayResponseType = HttpResponse<IInspection[]>;

@Injectable({ providedIn: 'root' })
export class InspectionService {
  public resourceUrl = SERVER_API_URL + 'api/inspections';

  constructor(protected http: HttpClient) {}

  create(inspection: IInspection): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(inspection);
    return this.http
      .post<IInspection>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(inspection: IInspection): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(inspection);
    return this.http
      .put<IInspection>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IInspection>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IInspection[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(inspection: IInspection): IInspection {
    const copy: IInspection = Object.assign({}, inspection, {
      insDate: inspection.insDate && inspection.insDate.isValid() ? inspection.insDate.toJSON() : undefined,
      insSysDate: inspection.insSysDate && inspection.insSysDate.isValid() ? inspection.insSysDate.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.insDate = res.body.insDate ? moment(res.body.insDate) : undefined;
      res.body.insSysDate = res.body.insSysDate ? moment(res.body.insSysDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((inspection: IInspection) => {
        inspection.insDate = inspection.insDate ? moment(inspection.insDate) : undefined;
        inspection.insSysDate = inspection.insSysDate ? moment(inspection.insSysDate) : undefined;
      });
    }
    return res;
  }
}
