import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOrderProcessStatus } from 'app/shared/model/order-process-status.model';

type EntityResponseType = HttpResponse<IOrderProcessStatus>;
type EntityArrayResponseType = HttpResponse<IOrderProcessStatus[]>;

@Injectable({ providedIn: 'root' })
export class OrderProcessStatusService {
  public resourceUrl = SERVER_API_URL + 'api/order-process-statuses';

  constructor(protected http: HttpClient) {}

  create(orderProcessStatus: IOrderProcessStatus): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(orderProcessStatus);
    return this.http
      .post<IOrderProcessStatus>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(orderProcessStatus: IOrderProcessStatus): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(orderProcessStatus);
    return this.http
      .put<IOrderProcessStatus>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IOrderProcessStatus>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOrderProcessStatus[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(orderProcessStatus: IOrderProcessStatus): IOrderProcessStatus {
    const copy: IOrderProcessStatus = Object.assign({}, orderProcessStatus, {
      statusUpdatedDate:
        orderProcessStatus.statusUpdatedDate && orderProcessStatus.statusUpdatedDate.isValid()
          ? orderProcessStatus.statusUpdatedDate.toJSON()
          : undefined,
      createdDate:
        orderProcessStatus.createdDate && orderProcessStatus.createdDate.isValid() ? orderProcessStatus.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        orderProcessStatus.lastUpdatedDate && orderProcessStatus.lastUpdatedDate.isValid()
          ? orderProcessStatus.lastUpdatedDate.toJSON()
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
      res.body.forEach((orderProcessStatus: IOrderProcessStatus) => {
        orderProcessStatus.statusUpdatedDate = orderProcessStatus.statusUpdatedDate
          ? moment(orderProcessStatus.statusUpdatedDate)
          : undefined;
        orderProcessStatus.createdDate = orderProcessStatus.createdDate ? moment(orderProcessStatus.createdDate) : undefined;
        orderProcessStatus.lastUpdatedDate = orderProcessStatus.lastUpdatedDate ? moment(orderProcessStatus.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
