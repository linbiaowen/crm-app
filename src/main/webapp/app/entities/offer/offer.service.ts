import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOffer } from 'app/shared/model/offer.model';

type EntityResponseType = HttpResponse<IOffer>;
type EntityArrayResponseType = HttpResponse<IOffer[]>;

@Injectable({ providedIn: 'root' })
export class OfferService {
  public resourceUrl = SERVER_API_URL + 'api/offers';

  constructor(protected http: HttpClient) {}

  create(offer: IOffer): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(offer);
    return this.http
      .post<IOffer>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(offer: IOffer): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(offer);
    return this.http
      .put<IOffer>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IOffer>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOffer[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(offer: IOffer): IOffer {
    const copy: IOffer = Object.assign({}, offer, {
      startDate: offer.startDate && offer.startDate.isValid() ? offer.startDate.toJSON() : undefined,
      endDate: offer.endDate && offer.endDate.isValid() ? offer.endDate.toJSON() : undefined,
      createdDate: offer.createdDate && offer.createdDate.isValid() ? offer.createdDate.toJSON() : undefined,
      lastUpdatedDate: offer.lastUpdatedDate && offer.lastUpdatedDate.isValid() ? offer.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((offer: IOffer) => {
        offer.startDate = offer.startDate ? moment(offer.startDate) : undefined;
        offer.endDate = offer.endDate ? moment(offer.endDate) : undefined;
        offer.createdDate = offer.createdDate ? moment(offer.createdDate) : undefined;
        offer.lastUpdatedDate = offer.lastUpdatedDate ? moment(offer.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
