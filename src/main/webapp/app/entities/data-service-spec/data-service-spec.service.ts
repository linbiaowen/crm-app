import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDataServiceSpec } from 'app/shared/model/data-service-spec.model';

type EntityResponseType = HttpResponse<IDataServiceSpec>;
type EntityArrayResponseType = HttpResponse<IDataServiceSpec[]>;

@Injectable({ providedIn: 'root' })
export class DataServiceSpecService {
  public resourceUrl = SERVER_API_URL + 'api/data-service-specs';

  constructor(protected http: HttpClient) {}

  create(dataServiceSpec: IDataServiceSpec): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(dataServiceSpec);
    return this.http
      .post<IDataServiceSpec>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(dataServiceSpec: IDataServiceSpec): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(dataServiceSpec);
    return this.http
      .put<IDataServiceSpec>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IDataServiceSpec>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDataServiceSpec[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(dataServiceSpec: IDataServiceSpec): IDataServiceSpec {
    const copy: IDataServiceSpec = Object.assign({}, dataServiceSpec, {
      createdDate: dataServiceSpec.createdDate && dataServiceSpec.createdDate.isValid() ? dataServiceSpec.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        dataServiceSpec.lastUpdatedDate && dataServiceSpec.lastUpdatedDate.isValid() ? dataServiceSpec.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((dataServiceSpec: IDataServiceSpec) => {
        dataServiceSpec.createdDate = dataServiceSpec.createdDate ? moment(dataServiceSpec.createdDate) : undefined;
        dataServiceSpec.lastUpdatedDate = dataServiceSpec.lastUpdatedDate ? moment(dataServiceSpec.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
