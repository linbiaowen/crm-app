import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOfferAdvancePayment } from 'app/shared/model/offer-advance-payment.model';

type EntityResponseType = HttpResponse<IOfferAdvancePayment>;
type EntityArrayResponseType = HttpResponse<IOfferAdvancePayment[]>;

@Injectable({ providedIn: 'root' })
export class OfferAdvancePaymentService {
  public resourceUrl = SERVER_API_URL + 'api/offer-advance-payments';

  constructor(protected http: HttpClient) {}

  create(offerAdvancePayment: IOfferAdvancePayment): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(offerAdvancePayment);
    return this.http
      .post<IOfferAdvancePayment>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(offerAdvancePayment: IOfferAdvancePayment): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(offerAdvancePayment);
    return this.http
      .put<IOfferAdvancePayment>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IOfferAdvancePayment>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOfferAdvancePayment[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(offerAdvancePayment: IOfferAdvancePayment): IOfferAdvancePayment {
    const copy: IOfferAdvancePayment = Object.assign({}, offerAdvancePayment, {
      createdDate:
        offerAdvancePayment.createdDate && offerAdvancePayment.createdDate.isValid() ? offerAdvancePayment.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        offerAdvancePayment.lastUpdatedDate && offerAdvancePayment.lastUpdatedDate.isValid()
          ? offerAdvancePayment.lastUpdatedDate.toJSON()
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
      res.body.forEach((offerAdvancePayment: IOfferAdvancePayment) => {
        offerAdvancePayment.createdDate = offerAdvancePayment.createdDate ? moment(offerAdvancePayment.createdDate) : undefined;
        offerAdvancePayment.lastUpdatedDate = offerAdvancePayment.lastUpdatedDate ? moment(offerAdvancePayment.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
