import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICertificateEmergencyText } from 'app/shared/model/certificate-emergency-text.model';

type EntityResponseType = HttpResponse<ICertificateEmergencyText>;
type EntityArrayResponseType = HttpResponse<ICertificateEmergencyText[]>;

@Injectable({ providedIn: 'root' })
export class CertificateEmergencyTextService {
  public resourceUrl = SERVER_API_URL + 'api/certificate-emergency-texts';

  constructor(protected http: HttpClient) {}

  create(certificateEmergencyText: ICertificateEmergencyText): Observable<EntityResponseType> {
    return this.http.post<ICertificateEmergencyText>(this.resourceUrl, certificateEmergencyText, { observe: 'response' });
  }

  update(certificateEmergencyText: ICertificateEmergencyText): Observable<EntityResponseType> {
    return this.http.put<ICertificateEmergencyText>(this.resourceUrl, certificateEmergencyText, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICertificateEmergencyText>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICertificateEmergencyText[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
