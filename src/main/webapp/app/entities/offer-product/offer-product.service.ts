import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOfferProduct } from 'app/shared/model/offer-product.model';

type EntityResponseType = HttpResponse<IOfferProduct>;
type EntityArrayResponseType = HttpResponse<IOfferProduct[]>;

@Injectable({ providedIn: 'root' })
export class OfferProductService {
  public resourceUrl = SERVER_API_URL + 'api/offer-products';

  constructor(protected http: HttpClient) {}

  create(offerProduct: IOfferProduct): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(offerProduct);
    return this.http
      .post<IOfferProduct>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(offerProduct: IOfferProduct): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(offerProduct);
    return this.http
      .put<IOfferProduct>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IOfferProduct>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOfferProduct[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(offerProduct: IOfferProduct): IOfferProduct {
    const copy: IOfferProduct = Object.assign({}, offerProduct, {
      createdDate: offerProduct.createdDate && offerProduct.createdDate.isValid() ? offerProduct.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        offerProduct.lastUpdatedDate && offerProduct.lastUpdatedDate.isValid() ? offerProduct.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((offerProduct: IOfferProduct) => {
        offerProduct.createdDate = offerProduct.createdDate ? moment(offerProduct.createdDate) : undefined;
        offerProduct.lastUpdatedDate = offerProduct.lastUpdatedDate ? moment(offerProduct.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
