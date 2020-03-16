import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISubscriptionProduct } from 'app/shared/model/subscription-product.model';

type EntityResponseType = HttpResponse<ISubscriptionProduct>;
type EntityArrayResponseType = HttpResponse<ISubscriptionProduct[]>;

@Injectable({ providedIn: 'root' })
export class SubscriptionProductService {
  public resourceUrl = SERVER_API_URL + 'api/subscription-products';

  constructor(protected http: HttpClient) {}

  create(subscriptionProduct: ISubscriptionProduct): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(subscriptionProduct);
    return this.http
      .post<ISubscriptionProduct>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(subscriptionProduct: ISubscriptionProduct): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(subscriptionProduct);
    return this.http
      .put<ISubscriptionProduct>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<ISubscriptionProduct>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISubscriptionProduct[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(subscriptionProduct: ISubscriptionProduct): ISubscriptionProduct {
    const copy: ISubscriptionProduct = Object.assign({}, subscriptionProduct, {
      activationDate:
        subscriptionProduct.activationDate && subscriptionProduct.activationDate.isValid()
          ? subscriptionProduct.activationDate.toJSON()
          : undefined,
      endDate: subscriptionProduct.endDate && subscriptionProduct.endDate.isValid() ? subscriptionProduct.endDate.toJSON() : undefined,
      createdDate:
        subscriptionProduct.createdDate && subscriptionProduct.createdDate.isValid() ? subscriptionProduct.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        subscriptionProduct.lastUpdatedDate && subscriptionProduct.lastUpdatedDate.isValid()
          ? subscriptionProduct.lastUpdatedDate.toJSON()
          : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.activationDate = res.body.activationDate ? moment(res.body.activationDate) : undefined;
      res.body.endDate = res.body.endDate ? moment(res.body.endDate) : undefined;
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.lastUpdatedDate = res.body.lastUpdatedDate ? moment(res.body.lastUpdatedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((subscriptionProduct: ISubscriptionProduct) => {
        subscriptionProduct.activationDate = subscriptionProduct.activationDate ? moment(subscriptionProduct.activationDate) : undefined;
        subscriptionProduct.endDate = subscriptionProduct.endDate ? moment(subscriptionProduct.endDate) : undefined;
        subscriptionProduct.createdDate = subscriptionProduct.createdDate ? moment(subscriptionProduct.createdDate) : undefined;
        subscriptionProduct.lastUpdatedDate = subscriptionProduct.lastUpdatedDate ? moment(subscriptionProduct.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
