import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICustAcctBlackList } from 'app/shared/model/cust-acct-black-list.model';

type EntityResponseType = HttpResponse<ICustAcctBlackList>;
type EntityArrayResponseType = HttpResponse<ICustAcctBlackList[]>;

@Injectable({ providedIn: 'root' })
export class CustAcctBlackListService {
  public resourceUrl = SERVER_API_URL + 'api/cust-acct-black-lists';

  constructor(protected http: HttpClient) {}

  create(custAcctBlackList: ICustAcctBlackList): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(custAcctBlackList);
    return this.http
      .post<ICustAcctBlackList>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(custAcctBlackList: ICustAcctBlackList): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(custAcctBlackList);
    return this.http
      .put<ICustAcctBlackList>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<ICustAcctBlackList>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICustAcctBlackList[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(custAcctBlackList: ICustAcctBlackList): ICustAcctBlackList {
    const copy: ICustAcctBlackList = Object.assign({}, custAcctBlackList, {
      startDate: custAcctBlackList.startDate && custAcctBlackList.startDate.isValid() ? custAcctBlackList.startDate.toJSON() : undefined,
      endDate: custAcctBlackList.endDate && custAcctBlackList.endDate.isValid() ? custAcctBlackList.endDate.toJSON() : undefined,
      createdDate:
        custAcctBlackList.createdDate && custAcctBlackList.createdDate.isValid() ? custAcctBlackList.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        custAcctBlackList.lastUpdatedDate && custAcctBlackList.lastUpdatedDate.isValid()
          ? custAcctBlackList.lastUpdatedDate.toJSON()
          : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.startDate = res.body.startDate ? moment(res.body.startDate) : undefined;
      res.body.endDate = res.body.endDate ? moment(res.body.endDate) : undefined;
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.lastUpdatedDate = res.body.lastUpdatedDate ? moment(res.body.lastUpdatedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((custAcctBlackList: ICustAcctBlackList) => {
        custAcctBlackList.startDate = custAcctBlackList.startDate ? moment(custAcctBlackList.startDate) : undefined;
        custAcctBlackList.endDate = custAcctBlackList.endDate ? moment(custAcctBlackList.endDate) : undefined;
        custAcctBlackList.createdDate = custAcctBlackList.createdDate ? moment(custAcctBlackList.createdDate) : undefined;
        custAcctBlackList.lastUpdatedDate = custAcctBlackList.lastUpdatedDate ? moment(custAcctBlackList.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
