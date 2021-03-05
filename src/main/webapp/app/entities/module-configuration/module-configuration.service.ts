import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IModuleConfiguration } from 'app/shared/model/module-configuration.model';

type EntityResponseType = HttpResponse<IModuleConfiguration>;
type EntityArrayResponseType = HttpResponse<IModuleConfiguration[]>;

@Injectable({ providedIn: 'root' })
export class ModuleConfigurationService {
  public resourceUrl = SERVER_API_URL + 'api/module-configurations';

  constructor(protected http: HttpClient) {}

  create(moduleConfiguration: IModuleConfiguration): Observable<EntityResponseType> {
    return this.http.post<IModuleConfiguration>(this.resourceUrl, moduleConfiguration, { observe: 'response' });
  }

  update(moduleConfiguration: IModuleConfiguration): Observable<EntityResponseType> {
    return this.http.put<IModuleConfiguration>(this.resourceUrl, moduleConfiguration, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IModuleConfiguration>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IModuleConfiguration[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
