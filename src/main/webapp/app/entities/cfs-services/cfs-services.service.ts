import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICfsServices } from 'app/shared/model/cfs-services.model';

type EntityResponseType = HttpResponse<ICfsServices>;
type EntityArrayResponseType = HttpResponse<ICfsServices[]>;

@Injectable({ providedIn: 'root' })
export class CfsServicesService {
  public resourceUrl = SERVER_API_URL + 'api/cfs-services';

  constructor(protected http: HttpClient) {}

  create(cfsServices: ICfsServices): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cfsServices);
    return this.http
      .post<ICfsServices>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(cfsServices: ICfsServices): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cfsServices);
    return this.http
      .put<ICfsServices>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<ICfsServices>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICfsServices[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(cfsServices: ICfsServices): ICfsServices {
    const copy: ICfsServices = Object.assign({}, cfsServices, {
      createdDate: cfsServices.createdDate && cfsServices.createdDate.isValid() ? cfsServices.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        cfsServices.lastUpdatedDate && cfsServices.lastUpdatedDate.isValid() ? cfsServices.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((cfsServices: ICfsServices) => {
        cfsServices.createdDate = cfsServices.createdDate ? moment(cfsServices.createdDate) : undefined;
        cfsServices.lastUpdatedDate = cfsServices.lastUpdatedDate ? moment(cfsServices.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
