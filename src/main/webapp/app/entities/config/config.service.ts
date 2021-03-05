import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IConfig } from 'app/shared/model/config.model';

type EntityResponseType = HttpResponse<IConfig>;
type EntityArrayResponseType = HttpResponse<IConfig[]>;

@Injectable({ providedIn: 'root' })
export class ConfigService {
  public resourceUrl = SERVER_API_URL + 'api/configs';

  constructor(protected http: HttpClient) {}

  create(config: IConfig): Observable<EntityResponseType> {
    return this.http.post<IConfig>(this.resourceUrl, config, { observe: 'response' });
  }

  update(config: IConfig): Observable<EntityResponseType> {
    return this.http.put<IConfig>(this.resourceUrl, config, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IConfig>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IConfig[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
