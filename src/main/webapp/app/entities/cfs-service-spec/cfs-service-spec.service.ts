import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICfsServiceSpec } from 'app/shared/model/cfs-service-spec.model';

type EntityResponseType = HttpResponse<ICfsServiceSpec>;
type EntityArrayResponseType = HttpResponse<ICfsServiceSpec[]>;

@Injectable({ providedIn: 'root' })
export class CfsServiceSpecService {
  public resourceUrl = SERVER_API_URL + 'api/cfs-service-specs';

  constructor(protected http: HttpClient) {}

  create(cfsServiceSpec: ICfsServiceSpec): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cfsServiceSpec);
    return this.http
      .post<ICfsServiceSpec>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(cfsServiceSpec: ICfsServiceSpec): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cfsServiceSpec);
    return this.http
      .put<ICfsServiceSpec>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<ICfsServiceSpec>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICfsServiceSpec[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(cfsServiceSpec: ICfsServiceSpec): ICfsServiceSpec {
    const copy: ICfsServiceSpec = Object.assign({}, cfsServiceSpec, {
      createdDate: cfsServiceSpec.createdDate && cfsServiceSpec.createdDate.isValid() ? cfsServiceSpec.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        cfsServiceSpec.lastUpdatedDate && cfsServiceSpec.lastUpdatedDate.isValid() ? cfsServiceSpec.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((cfsServiceSpec: ICfsServiceSpec) => {
        cfsServiceSpec.createdDate = cfsServiceSpec.createdDate ? moment(cfsServiceSpec.createdDate) : undefined;
        cfsServiceSpec.lastUpdatedDate = cfsServiceSpec.lastUpdatedDate ? moment(cfsServiceSpec.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
