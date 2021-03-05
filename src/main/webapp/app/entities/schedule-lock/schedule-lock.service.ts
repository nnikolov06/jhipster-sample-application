import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IScheduleLock } from 'app/shared/model/schedule-lock.model';

type EntityResponseType = HttpResponse<IScheduleLock>;
type EntityArrayResponseType = HttpResponse<IScheduleLock[]>;

@Injectable({ providedIn: 'root' })
export class ScheduleLockService {
  public resourceUrl = SERVER_API_URL + 'api/schedule-locks';

  constructor(protected http: HttpClient) {}

  create(scheduleLock: IScheduleLock): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(scheduleLock);
    return this.http
      .post<IScheduleLock>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(scheduleLock: IScheduleLock): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(scheduleLock);
    return this.http
      .put<IScheduleLock>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IScheduleLock>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IScheduleLock[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(scheduleLock: IScheduleLock): IScheduleLock {
    const copy: IScheduleLock = Object.assign({}, scheduleLock, {
      slcLockUntil: scheduleLock.slcLockUntil && scheduleLock.slcLockUntil.isValid() ? scheduleLock.slcLockUntil.toJSON() : undefined,
      slcLockedAt: scheduleLock.slcLockedAt && scheduleLock.slcLockedAt.isValid() ? scheduleLock.slcLockedAt.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.slcLockUntil = res.body.slcLockUntil ? moment(res.body.slcLockUntil) : undefined;
      res.body.slcLockedAt = res.body.slcLockedAt ? moment(res.body.slcLockedAt) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((scheduleLock: IScheduleLock) => {
        scheduleLock.slcLockUntil = scheduleLock.slcLockUntil ? moment(scheduleLock.slcLockUntil) : undefined;
        scheduleLock.slcLockedAt = scheduleLock.slcLockedAt ? moment(scheduleLock.slcLockedAt) : undefined;
      });
    }
    return res;
  }
}
