import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISubscriptionGroup } from 'app/shared/model/subscription-group.model';

type EntityResponseType = HttpResponse<ISubscriptionGroup>;
type EntityArrayResponseType = HttpResponse<ISubscriptionGroup[]>;

@Injectable({ providedIn: 'root' })
export class SubscriptionGroupService {
  public resourceUrl = SERVER_API_URL + 'api/subscription-groups';

  constructor(protected http: HttpClient) {}

  create(subscriptionGroup: ISubscriptionGroup): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(subscriptionGroup);
    return this.http
      .post<ISubscriptionGroup>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(subscriptionGroup: ISubscriptionGroup): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(subscriptionGroup);
    return this.http
      .put<ISubscriptionGroup>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<ISubscriptionGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISubscriptionGroup[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(subscriptionGroup: ISubscriptionGroup): ISubscriptionGroup {
    const copy: ISubscriptionGroup = Object.assign({}, subscriptionGroup, {
      startDate: subscriptionGroup.startDate && subscriptionGroup.startDate.isValid() ? subscriptionGroup.startDate.toJSON() : undefined,
      endDate: subscriptionGroup.endDate && subscriptionGroup.endDate.isValid() ? subscriptionGroup.endDate.toJSON() : undefined,
      createdDate:
        subscriptionGroup.createdDate && subscriptionGroup.createdDate.isValid() ? subscriptionGroup.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        subscriptionGroup.lastUpdatedDate && subscriptionGroup.lastUpdatedDate.isValid()
          ? subscriptionGroup.lastUpdatedDate.toJSON()
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
      res.body.forEach((subscriptionGroup: ISubscriptionGroup) => {
        subscriptionGroup.startDate = subscriptionGroup.startDate ? moment(subscriptionGroup.startDate) : undefined;
        subscriptionGroup.endDate = subscriptionGroup.endDate ? moment(subscriptionGroup.endDate) : undefined;
        subscriptionGroup.createdDate = subscriptionGroup.createdDate ? moment(subscriptionGroup.createdDate) : undefined;
        subscriptionGroup.lastUpdatedDate = subscriptionGroup.lastUpdatedDate ? moment(subscriptionGroup.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
