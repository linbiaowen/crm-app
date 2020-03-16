import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOrderMaster } from 'app/shared/model/order-master.model';

type EntityResponseType = HttpResponse<IOrderMaster>;
type EntityArrayResponseType = HttpResponse<IOrderMaster[]>;

@Injectable({ providedIn: 'root' })
export class OrderMasterService {
  public resourceUrl = SERVER_API_URL + 'api/order-masters';

  constructor(protected http: HttpClient) {}

  create(orderMaster: IOrderMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(orderMaster);
    return this.http
      .post<IOrderMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(orderMaster: IOrderMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(orderMaster);
    return this.http
      .put<IOrderMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IOrderMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOrderMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(orderMaster: IOrderMaster): IOrderMaster {
    const copy: IOrderMaster = Object.assign({}, orderMaster, {
      createdDate: orderMaster.createdDate && orderMaster.createdDate.isValid() ? orderMaster.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        orderMaster.lastUpdatedDate && orderMaster.lastUpdatedDate.isValid() ? orderMaster.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((orderMaster: IOrderMaster) => {
        orderMaster.createdDate = orderMaster.createdDate ? moment(orderMaster.createdDate) : undefined;
        orderMaster.lastUpdatedDate = orderMaster.lastUpdatedDate ? moment(orderMaster.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
