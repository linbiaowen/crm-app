import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVoiceServiceSpec } from 'app/shared/model/voice-service-spec.model';

type EntityResponseType = HttpResponse<IVoiceServiceSpec>;
type EntityArrayResponseType = HttpResponse<IVoiceServiceSpec[]>;

@Injectable({ providedIn: 'root' })
export class VoiceServiceSpecService {
  public resourceUrl = SERVER_API_URL + 'api/voice-service-specs';

  constructor(protected http: HttpClient) {}

  create(voiceServiceSpec: IVoiceServiceSpec): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(voiceServiceSpec);
    return this.http
      .post<IVoiceServiceSpec>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(voiceServiceSpec: IVoiceServiceSpec): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(voiceServiceSpec);
    return this.http
      .put<IVoiceServiceSpec>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IVoiceServiceSpec>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IVoiceServiceSpec[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(voiceServiceSpec: IVoiceServiceSpec): IVoiceServiceSpec {
    const copy: IVoiceServiceSpec = Object.assign({}, voiceServiceSpec, {
      createdDate:
        voiceServiceSpec.createdDate && voiceServiceSpec.createdDate.isValid() ? voiceServiceSpec.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        voiceServiceSpec.lastUpdatedDate && voiceServiceSpec.lastUpdatedDate.isValid()
          ? voiceServiceSpec.lastUpdatedDate.toJSON()
          : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.lastUpdatedDate = res.body.lastUpdatedDate ? moment(res.body.lastUpdatedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((voiceServiceSpec: IVoiceServiceSpec) => {
        voiceServiceSpec.createdDate = voiceServiceSpec.createdDate ? moment(voiceServiceSpec.createdDate) : undefined;
        voiceServiceSpec.lastUpdatedDate = voiceServiceSpec.lastUpdatedDate ? moment(voiceServiceSpec.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
