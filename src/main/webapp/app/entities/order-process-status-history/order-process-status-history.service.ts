import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOrderProcessStatusHistory } from 'app/shared/model/order-process-status-history.model';

type EntityResponseType = HttpResponse<IOrderProcessStatusHistory>;
type EntityArrayResponseType = HttpResponse<IOrderProcessStatusHistory[]>;

@Injectable({ providedIn: 'root' })
export class OrderProcessStatusHistoryService {
  public resourceUrl = SERVER_API_URL + 'api/order-process-status-histories';

  constructor(protected http: HttpClient) {}

  create(orderProcessStatusHistory: IOrderProcessStatusHistory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(orderProcessStatusHistory);
    return this.http
      .post<IOrderProcessStatusHistory>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(orderProcessStatusHistory: IOrderProcessStatusHistory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(orderProcessStatusHistory);
    return this.http
      .put<IOrderProcessStatusHistory>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IOrderProcessStatusHistory>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOrderProcessStatusHistory[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(orderProcessStatusHistory: IOrderProcessStatusHistory): IOrderProcessStatusHistory {
    const copy: IOrderProcessStatusHistory = Object.assign({}, orderProcessStatusHistory, {
      statusUpdatedDate:
        orderProcessStatusHistory.statusUpdatedDate && orderProcessStatusHistory.statusUpdatedDate.isValid()
          ? orderProcessStatusHistory.statusUpdatedDate.toJSON()
          : undefined,
      createdDate:
        orderProcessStatusHistory.createdDate && orderProcessStatusHistory.createdDate.isValid()
          ? orderProcessStatusHistory.createdDate.toJSON()
          : undefined,
      lastUpdatedDate:
        orderProcessStatusHistory.lastUpdatedDate && orderProcessStatusHistory.lastUpdatedDate.isValid()
          ? orderProcessStatusHistory.lastUpdatedDate.toJSON()
          : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.statusUpdatedDate = res.body.statusUpdatedDate ? moment(res.body.statusUpdatedDate) : undefined;
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.lastUpdatedDate = res.body.lastUpdatedDate ? moment(res.body.lastUpdatedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((orderProcessStatusHistory: IOrderProcessStatusHistory) => {
        orderProcessStatusHistory.statusUpdatedDate = orderProcessStatusHistory.statusUpdatedDate
          ? moment(orderProcessStatusHistory.statusUpdatedDate)
          : undefined;
        orderProcessStatusHistory.createdDate = orderProcessStatusHistory.createdDate
          ? moment(orderProcessStatusHistory.createdDate)
          : undefined;
        orderProcessStatusHistory.lastUpdatedDate = orderProcessStatusHistory.lastUpdatedDate
          ? moment(orderProcessStatusHistory.lastUpdatedDate)
          : undefined;
      });
    }
    return res;
  }
}
