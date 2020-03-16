import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICustSubscription } from 'app/shared/model/cust-subscription.model';

type EntityResponseType = HttpResponse<ICustSubscription>;
type EntityArrayResponseType = HttpResponse<ICustSubscription[]>;

@Injectable({ providedIn: 'root' })
export class CustSubscriptionService {
  public resourceUrl = SERVER_API_URL + 'api/cust-subscriptions';

  constructor(protected http: HttpClient) {}

  create(custSubscription: ICustSubscription): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(custSubscription);
    return this.http
      .post<ICustSubscription>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(custSubscription: ICustSubscription): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(custSubscription);
    return this.http
      .put<ICustSubscription>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<ICustSubscription>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICustSubscription[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(custSubscription: ICustSubscription): ICustSubscription {
    const copy: ICustSubscription = Object.assign({}, custSubscription, {
      activationDate:
        custSubscription.activationDate && custSubscription.activationDate.isValid() ? custSubscription.activationDate.toJSON() : undefined,
      subsEndDate:
        custSubscription.subsEndDate && custSubscription.subsEndDate.isValid() ? custSubscription.subsEndDate.toJSON() : undefined,
      subsPurchaseDate:
        custSubscription.subsPurchaseDate && custSubscription.subsPurchaseDate.isValid()
          ? custSubscription.subsPurchaseDate.toJSON()
          : undefined,
      origServiceStartDate:
        custSubscription.origServiceStartDate && custSubscription.origServiceStartDate.isValid()
          ? custSubscription.origServiceStartDate.toJSON()
          : undefined,
      lastStatusUpdatedDate:
        custSubscription.lastStatusUpdatedDate && custSubscription.lastStatusUpdatedDate.isValid()
          ? custSubscription.lastStatusUpdatedDate.toJSON()
          : undefined,
      createdDate:
        custSubscription.createdDate && custSubscription.createdDate.isValid() ? custSubscription.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        custSubscription.lastUpdatedDate && custSubscription.lastUpdatedDate.isValid()
          ? custSubscription.lastUpdatedDate.toJSON()
          : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.activationDate = res.body.activationDate ? moment(res.body.activationDate) : undefined;
      res.body.subsEndDate = res.body.subsEndDate ? moment(res.body.subsEndDate) : undefined;
      res.body.subsPurchaseDate = res.body.subsPurchaseDate ? moment(res.body.subsPurchaseDate) : undefined;
      res.body.origServiceStartDate = res.body.origServiceStartDate ? moment(res.body.origServiceStartDate) : undefined;
      res.body.lastStatusUpdatedDate = res.body.lastStatusUpdatedDate ? moment(res.body.lastStatusUpdatedDate) : undefined;
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.lastUpdatedDate = res.body.lastUpdatedDate ? moment(res.body.lastUpdatedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((custSubscription: ICustSubscription) => {
        custSubscription.activationDate = custSubscription.activationDate ? moment(custSubscription.activationDate) : undefined;
        custSubscription.subsEndDate = custSubscription.subsEndDate ? moment(custSubscription.subsEndDate) : undefined;
        custSubscription.subsPurchaseDate = custSubscription.subsPurchaseDate ? moment(custSubscription.subsPurchaseDate) : undefined;
        custSubscription.origServiceStartDate = custSubscription.origServiceStartDate
          ? moment(custSubscription.origServiceStartDate)
          : undefined;
        custSubscription.lastStatusUpdatedDate = custSubscription.lastStatusUpdatedDate
          ? moment(custSubscription.lastStatusUpdatedDate)
          : undefined;
        custSubscription.createdDate = custSubscription.createdDate ? moment(custSubscription.createdDate) : undefined;
        custSubscription.lastUpdatedDate = custSubscription.lastUpdatedDate ? moment(custSubscription.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
