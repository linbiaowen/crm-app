import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICustDocDataStore } from 'app/shared/model/cust-doc-data-store.model';

type EntityResponseType = HttpResponse<ICustDocDataStore>;
type EntityArrayResponseType = HttpResponse<ICustDocDataStore[]>;

@Injectable({ providedIn: 'root' })
export class CustDocDataStoreService {
  public resourceUrl = SERVER_API_URL + 'api/cust-doc-data-stores';

  constructor(protected http: HttpClient) {}

  create(custDocDataStore: ICustDocDataStore): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(custDocDataStore);
    return this.http
      .post<ICustDocDataStore>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(custDocDataStore: ICustDocDataStore): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(custDocDataStore);
    return this.http
      .put<ICustDocDataStore>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<ICustDocDataStore>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICustDocDataStore[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(custDocDataStore: ICustDocDataStore): ICustDocDataStore {
    const copy: ICustDocDataStore = Object.assign({}, custDocDataStore, {
      createdDate:
        custDocDataStore.createdDate && custDocDataStore.createdDate.isValid() ? custDocDataStore.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        custDocDataStore.lastUpdatedDate && custDocDataStore.lastUpdatedDate.isValid()
          ? custDocDataStore.lastUpdatedDate.toJSON()
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
      res.body.forEach((custDocDataStore: ICustDocDataStore) => {
        custDocDataStore.createdDate = custDocDataStore.createdDate ? moment(custDocDataStore.createdDate) : undefined;
        custDocDataStore.lastUpdatedDate = custDocDataStore.lastUpdatedDate ? moment(custDocDataStore.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
