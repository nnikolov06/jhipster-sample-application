import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRezmaEntity } from 'app/shared/model/rezma-entity.model';

type EntityResponseType = HttpResponse<IRezmaEntity>;
type EntityArrayResponseType = HttpResponse<IRezmaEntity[]>;

@Injectable({ providedIn: 'root' })
export class RezmaEntityService {
  public resourceUrl = SERVER_API_URL + 'api/rezma-entities';

  constructor(protected http: HttpClient) {}

  create(rezmaEntity: IRezmaEntity): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rezmaEntity);
    return this.http
      .post<IRezmaEntity>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(rezmaEntity: IRezmaEntity): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rezmaEntity);
    return this.http
      .put<IRezmaEntity>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRezmaEntity>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRezmaEntity[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(rezmaEntity: IRezmaEntity): IRezmaEntity {
    const copy: IRezmaEntity = Object.assign({}, rezmaEntity, {
      entSystemDate: rezmaEntity.entSystemDate && rezmaEntity.entSystemDate.isValid() ? rezmaEntity.entSystemDate.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.entSystemDate = res.body.entSystemDate ? moment(res.body.entSystemDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((rezmaEntity: IRezmaEntity) => {
        rezmaEntity.entSystemDate = rezmaEntity.entSystemDate ? moment(rezmaEntity.entSystemDate) : undefined;
      });
    }
    return res;
  }
}
