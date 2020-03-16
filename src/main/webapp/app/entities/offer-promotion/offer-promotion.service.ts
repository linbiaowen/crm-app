import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOfferPromotion } from 'app/shared/model/offer-promotion.model';

type EntityResponseType = HttpResponse<IOfferPromotion>;
type EntityArrayResponseType = HttpResponse<IOfferPromotion[]>;

@Injectable({ providedIn: 'root' })
export class OfferPromotionService {
  public resourceUrl = SERVER_API_URL + 'api/offer-promotions';

  constructor(protected http: HttpClient) {}

  create(offerPromotion: IOfferPromotion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(offerPromotion);
    return this.http
      .post<IOfferPromotion>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(offerPromotion: IOfferPromotion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(offerPromotion);
    return this.http
      .put<IOfferPromotion>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IOfferPromotion>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOfferPromotion[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(offerPromotion: IOfferPromotion): IOfferPromotion {
    const copy: IOfferPromotion = Object.assign({}, offerPromotion, {
      startDate: offerPromotion.startDate && offerPromotion.startDate.isValid() ? offerPromotion.startDate.toJSON() : undefined,
      endDate: offerPromotion.endDate && offerPromotion.endDate.isValid() ? offerPromotion.endDate.toJSON() : undefined,
      createdDate: offerPromotion.createdDate && offerPromotion.createdDate.isValid() ? offerPromotion.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        offerPromotion.lastUpdatedDate && offerPromotion.lastUpdatedDate.isValid() ? offerPromotion.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((offerPromotion: IOfferPromotion) => {
        offerPromotion.startDate = offerPromotion.startDate ? moment(offerPromotion.startDate) : undefined;
        offerPromotion.endDate = offerPromotion.endDate ? moment(offerPromotion.endDate) : undefined;
        offerPromotion.createdDate = offerPromotion.createdDate ? moment(offerPromotion.createdDate) : undefined;
        offerPromotion.lastUpdatedDate = offerPromotion.lastUpdatedDate ? moment(offerPromotion.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
