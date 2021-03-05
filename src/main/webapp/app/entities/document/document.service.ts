import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDocument } from 'app/shared/model/document.model';

type EntityResponseType = HttpResponse<IDocument>;
type EntityArrayResponseType = HttpResponse<IDocument[]>;

@Injectable({ providedIn: 'root' })
export class DocumentService {
  public resourceUrl = SERVER_API_URL + 'api/documents';

  constructor(protected http: HttpClient) {}

  create(document: IDocument): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(document);
    return this.http
      .post<IDocument>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(document: IDocument): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(document);
    return this.http
      .put<IDocument>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDocument>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDocument[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(document: IDocument): IDocument {
    const copy: IDocument = Object.assign({}, document, {
      docDate: document.docDate && document.docDate.isValid() ? document.docDate.format(DATE_FORMAT) : undefined,
      docSysDate: document.docSysDate && document.docSysDate.isValid() ? document.docSysDate.toJSON() : undefined,
      docLastChangeDate:
        document.docLastChangeDate && document.docLastChangeDate.isValid() ? document.docLastChangeDate.toJSON() : undefined,
      docIntoForceDate:
        document.docIntoForceDate && document.docIntoForceDate.isValid() ? document.docIntoForceDate.format(DATE_FORMAT) : undefined,
      docDeferredDate:
        document.docDeferredDate && document.docDeferredDate.isValid() ? document.docDeferredDate.format(DATE_FORMAT) : undefined,
      docRescheduledDate:
        document.docRescheduledDate && document.docRescheduledDate.isValid() ? document.docRescheduledDate.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.docDate = res.body.docDate ? moment(res.body.docDate) : undefined;
      res.body.docSysDate = res.body.docSysDate ? moment(res.body.docSysDate) : undefined;
      res.body.docLastChangeDate = res.body.docLastChangeDate ? moment(res.body.docLastChangeDate) : undefined;
      res.body.docIntoForceDate = res.body.docIntoForceDate ? moment(res.body.docIntoForceDate) : undefined;
      res.body.docDeferredDate = res.body.docDeferredDate ? moment(res.body.docDeferredDate) : undefined;
      res.body.docRescheduledDate = res.body.docRescheduledDate ? moment(res.body.docRescheduledDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((document: IDocument) => {
        document.docDate = document.docDate ? moment(document.docDate) : undefined;
        document.docSysDate = document.docSysDate ? moment(document.docSysDate) : undefined;
        document.docLastChangeDate = document.docLastChangeDate ? moment(document.docLastChangeDate) : undefined;
        document.docIntoForceDate = document.docIntoForceDate ? moment(document.docIntoForceDate) : undefined;
        document.docDeferredDate = document.docDeferredDate ? moment(document.docDeferredDate) : undefined;
        document.docRescheduledDate = document.docRescheduledDate ? moment(document.docRescheduledDate) : undefined;
      });
    }
    return res;
  }
}
