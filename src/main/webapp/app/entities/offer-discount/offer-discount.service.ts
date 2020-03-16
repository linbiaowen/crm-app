import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOfferDiscount } from 'app/shared/model/offer-discount.model';

type EntityResponseType = HttpResponse<IOfferDiscount>;
type EntityArrayResponseType = HttpResponse<IOfferDiscount[]>;

@Injectable({ providedIn: 'root' })
export class OfferDiscountService {
  public resourceUrl = SERVER_API_URL + 'api/offer-discounts';

  constructor(protected http: HttpClient) {}

  create(offerDiscount: IOfferDiscount): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(offerDiscount);
    return this.http
      .post<IOfferDiscount>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(offerDiscount: IOfferDiscount): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(offerDiscount);
    return this.http
      .put<IOfferDiscount>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IOfferDiscount>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOfferDiscount[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(offerDiscount: IOfferDiscount): IOfferDiscount {
    const copy: IOfferDiscount = Object.assign({}, offerDiscount, {
      startDate: offerDiscount.startDate && offerDiscount.startDate.isValid() ? offerDiscount.startDate.toJSON() : undefined,
      endDate: offerDiscount.endDate && offerDiscount.endDate.isValid() ? offerDiscount.endDate.toJSON() : undefined,
      createdDate: offerDiscount.createdDate && offerDiscount.createdDate.isValid() ? offerDiscount.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        offerDiscount.lastUpdatedDate && offerDiscount.lastUpdatedDate.isValid() ? offerDiscount.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((offerDiscount: IOfferDiscount) => {
        offerDiscount.startDate = offerDiscount.startDate ? moment(offerDiscount.startDate) : undefined;
        offerDiscount.endDate = offerDiscount.endDate ? moment(offerDiscount.endDate) : undefined;
        offerDiscount.createdDate = offerDiscount.createdDate ? moment(offerDiscount.createdDate) : undefined;
        offerDiscount.lastUpdatedDate = offerDiscount.lastUpdatedDate ? moment(offerDiscount.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
