import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEmergencyProcedure } from 'app/shared/model/emergency-procedure.model';

type EntityResponseType = HttpResponse<IEmergencyProcedure>;
type EntityArrayResponseType = HttpResponse<IEmergencyProcedure[]>;

@Injectable({ providedIn: 'root' })
export class EmergencyProcedureService {
  public resourceUrl = SERVER_API_URL + 'api/emergency-procedures';

  constructor(protected http: HttpClient) {}

  create(emergencyProcedure: IEmergencyProcedure): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(emergencyProcedure);
    return this.http
      .post<IEmergencyProcedure>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(emergencyProcedure: IEmergencyProcedure): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(emergencyProcedure);
    return this.http
      .put<IEmergencyProcedure>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEmergencyProcedure>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IEmergencyProcedure[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(emergencyProcedure: IEmergencyProcedure): IEmergencyProcedure {
    const copy: IEmergencyProcedure = Object.assign({}, emergencyProcedure, {
      empDateStart:
        emergencyProcedure.empDateStart && emergencyProcedure.empDateStart.isValid() ? emergencyProcedure.empDateStart.toJSON() : undefined,
      empDateEnd:
        emergencyProcedure.empDateEnd && emergencyProcedure.empDateEnd.isValid() ? emergencyProcedure.empDateEnd.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.empDateStart = res.body.empDateStart ? moment(res.body.empDateStart) : undefined;
      res.body.empDateEnd = res.body.empDateEnd ? moment(res.body.empDateEnd) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((emergencyProcedure: IEmergencyProcedure) => {
        emergencyProcedure.empDateStart = emergencyProcedure.empDateStart ? moment(emergencyProcedure.empDateStart) : undefined;
        emergencyProcedure.empDateEnd = emergencyProcedure.empDateEnd ? moment(emergencyProcedure.empDateEnd) : undefined;
      });
    }
    return res;
  }
}
