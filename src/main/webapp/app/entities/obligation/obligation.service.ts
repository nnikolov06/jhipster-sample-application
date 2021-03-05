import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IObligation } from 'app/shared/model/obligation.model';

type EntityResponseType = HttpResponse<IObligation>;
type EntityArrayResponseType = HttpResponse<IObligation[]>;

@Injectable({ providedIn: 'root' })
export class ObligationService {
  public resourceUrl = SERVER_API_URL + 'api/obligations';

  constructor(protected http: HttpClient) {}

  create(obligation: IObligation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(obligation);
    return this.http
      .post<IObligation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(obligation: IObligation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(obligation);
    return this.http
      .put<IObligation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IObligation>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IObligation[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(obligation: IObligation): IObligation {
    const copy: IObligation = Object.assign({}, obligation, {
      oblSysDate: obligation.oblSysDate && obligation.oblSysDate.isValid() ? obligation.oblSysDate.toJSON() : undefined,
      oblMraDate: obligation.oblMraDate && obligation.oblMraDate.isValid() ? obligation.oblMraDate.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.oblSysDate = res.body.oblSysDate ? moment(res.body.oblSysDate) : undefined;
      res.body.oblMraDate = res.body.oblMraDate ? moment(res.body.oblMraDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((obligation: IObligation) => {
        obligation.oblSysDate = obligation.oblSysDate ? moment(obligation.oblSysDate) : undefined;
        obligation.oblMraDate = obligation.oblMraDate ? moment(obligation.oblMraDate) : undefined;
      });
    }
    return res;
  }
}
