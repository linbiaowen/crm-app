import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOrderProcessConfig } from 'app/shared/model/order-process-config.model';

type EntityResponseType = HttpResponse<IOrderProcessConfig>;
type EntityArrayResponseType = HttpResponse<IOrderProcessConfig[]>;

@Injectable({ providedIn: 'root' })
export class OrderProcessConfigService {
  public resourceUrl = SERVER_API_URL + 'api/order-process-configs';

  constructor(protected http: HttpClient) {}

  create(orderProcessConfig: IOrderProcessConfig): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(orderProcessConfig);
    return this.http
      .post<IOrderProcessConfig>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(orderProcessConfig: IOrderProcessConfig): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(orderProcessConfig);
    return this.http
      .put<IOrderProcessConfig>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IOrderProcessConfig>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOrderProcessConfig[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(orderProcessConfig: IOrderProcessConfig): IOrderProcessConfig {
    const copy: IOrderProcessConfig = Object.assign({}, orderProcessConfig, {
      createdDate:
        orderProcessConfig.createdDate && orderProcessConfig.createdDate.isValid() ? orderProcessConfig.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        orderProcessConfig.lastUpdatedDate && orderProcessConfig.lastUpdatedDate.isValid()
          ? orderProcessConfig.lastUpdatedDate.toJSON()
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
      res.body.forEach((orderProcessConfig: IOrderProcessConfig) => {
        orderProcessConfig.createdDate = orderProcessConfig.createdDate ? moment(orderProcessConfig.createdDate) : undefined;
        orderProcessConfig.lastUpdatedDate = orderProcessConfig.lastUpdatedDate ? moment(orderProcessConfig.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
