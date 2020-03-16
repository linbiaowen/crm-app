import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISubscriptionProvision } from 'app/shared/model/subscription-provision.model';

type EntityResponseType = HttpResponse<ISubscriptionProvision>;
type EntityArrayResponseType = HttpResponse<ISubscriptionProvision[]>;

@Injectable({ providedIn: 'root' })
export class SubscriptionProvisionService {
  public resourceUrl = SERVER_API_URL + 'api/subscription-provisions';

  constructor(protected http: HttpClient) {}

  create(subscriptionProvision: ISubscriptionProvision): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(subscriptionProvision);
    return this.http
      .post<ISubscriptionProvision>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(subscriptionProvision: ISubscriptionProvision): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(subscriptionProvision);
    return this.http
      .put<ISubscriptionProvision>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<ISubscriptionProvision>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISubscriptionProvision[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(subscriptionProvision: ISubscriptionProvision): ISubscriptionProvision {
    const copy: ISubscriptionProvision = Object.assign({}, subscriptionProvision, {
      startDate:
        subscriptionProvision.startDate && subscriptionProvision.startDate.isValid() ? subscriptionProvision.startDate.toJSON() : undefined,
      endDate:
        subscriptionProvision.endDate && subscriptionProvision.endDate.isValid() ? subscriptionProvision.endDate.toJSON() : undefined,
      createdDate:
        subscriptionProvision.createdDate && subscriptionProvision.createdDate.isValid()
          ? subscriptionProvision.createdDate.toJSON()
          : undefined,
      lastUpdatedDate:
        subscriptionProvision.lastUpdatedDate && subscriptionProvision.lastUpdatedDate.isValid()
          ? subscriptionProvision.lastUpdatedDate.toJSON()
          : undefined
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
      res.body.forEach((subscriptionProvision: ISubscriptionProvision) => {
        subscriptionProvision.startDate = subscriptionProvision.startDate ? moment(subscriptionProvision.startDate) : undefined;
        subscriptionProvision.endDate = subscriptionProvision.endDate ? moment(subscriptionProvision.endDate) : undefined;
        subscriptionProvision.createdDate = subscriptionProvision.createdDate ? moment(subscriptionProvision.createdDate) : undefined;
        subscriptionProvision.lastUpdatedDate = subscriptionProvision.lastUpdatedDate
          ? moment(subscriptionProvision.lastUpdatedDate)
          : undefined;
      });
    }
    return res;
  }
}
