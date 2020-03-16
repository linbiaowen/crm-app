import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDeliveryMethod } from 'app/shared/model/delivery-method.model';

type EntityResponseType = HttpResponse<IDeliveryMethod>;
type EntityArrayResponseType = HttpResponse<IDeliveryMethod[]>;

@Injectable({ providedIn: 'root' })
export class DeliveryMethodService {
  public resourceUrl = SERVER_API_URL + 'api/delivery-methods';

  constructor(protected http: HttpClient) {}

  create(deliveryMethod: IDeliveryMethod): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(deliveryMethod);
    return this.http
      .post<IDeliveryMethod>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(deliveryMethod: IDeliveryMethod): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(deliveryMethod);
    return this.http
      .put<IDeliveryMethod>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IDeliveryMethod>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDeliveryMethod[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(deliveryMethod: IDeliveryMethod): IDeliveryMethod {
    const copy: IDeliveryMethod = Object.assign({}, deliveryMethod, {
      startDate: deliveryMethod.startDate && deliveryMethod.startDate.isValid() ? deliveryMethod.startDate.toJSON() : undefined,
      endDate: deliveryMethod.endDate && deliveryMethod.endDate.isValid() ? deliveryMethod.endDate.toJSON() : undefined,
      createdDate: deliveryMethod.createdDate && deliveryMethod.createdDate.isValid() ? deliveryMethod.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        deliveryMethod.lastUpdatedDate && deliveryMethod.lastUpdatedDate.isValid() ? deliveryMethod.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((deliveryMethod: IDeliveryMethod) => {
        deliveryMethod.startDate = deliveryMethod.startDate ? moment(deliveryMethod.startDate) : undefined;
        deliveryMethod.endDate = deliveryMethod.endDate ? moment(deliveryMethod.endDate) : undefined;
        deliveryMethod.createdDate = deliveryMethod.createdDate ? moment(deliveryMethod.createdDate) : undefined;
        deliveryMethod.lastUpdatedDate = deliveryMethod.lastUpdatedDate ? moment(deliveryMethod.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
