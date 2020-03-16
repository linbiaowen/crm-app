import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISubscriptionDetails } from 'app/shared/model/subscription-details.model';

type EntityResponseType = HttpResponse<ISubscriptionDetails>;
type EntityArrayResponseType = HttpResponse<ISubscriptionDetails[]>;

@Injectable({ providedIn: 'root' })
export class SubscriptionDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/subscription-details';

  constructor(protected http: HttpClient) {}

  create(subscriptionDetails: ISubscriptionDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(subscriptionDetails);
    return this.http
      .post<ISubscriptionDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(subscriptionDetails: ISubscriptionDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(subscriptionDetails);
    return this.http
      .put<ISubscriptionDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<ISubscriptionDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISubscriptionDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(subscriptionDetails: ISubscriptionDetails): ISubscriptionDetails {
    const copy: ISubscriptionDetails = Object.assign({}, subscriptionDetails, {
      startDate:
        subscriptionDetails.startDate && subscriptionDetails.startDate.isValid() ? subscriptionDetails.startDate.toJSON() : undefined,
      endDate: subscriptionDetails.endDate && subscriptionDetails.endDate.isValid() ? subscriptionDetails.endDate.toJSON() : undefined,
      mnpRequestedDate:
        subscriptionDetails.mnpRequestedDate && subscriptionDetails.mnpRequestedDate.isValid()
          ? subscriptionDetails.mnpRequestedDate.toJSON()
          : undefined,
      createdDate:
        subscriptionDetails.createdDate && subscriptionDetails.createdDate.isValid() ? subscriptionDetails.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        subscriptionDetails.lastUpdatedDate && subscriptionDetails.lastUpdatedDate.isValid()
          ? subscriptionDetails.lastUpdatedDate.toJSON()
          : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.startDate = res.body.startDate ? moment(res.body.startDate) : undefined;
      res.body.endDate = res.body.endDate ? moment(res.body.endDate) : undefined;
      res.body.mnpRequestedDate = res.body.mnpRequestedDate ? moment(res.body.mnpRequestedDate) : undefined;
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.lastUpdatedDate = res.body.lastUpdatedDate ? moment(res.body.lastUpdatedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((subscriptionDetails: ISubscriptionDetails) => {
        subscriptionDetails.startDate = subscriptionDetails.startDate ? moment(subscriptionDetails.startDate) : undefined;
        subscriptionDetails.endDate = subscriptionDetails.endDate ? moment(subscriptionDetails.endDate) : undefined;
        subscriptionDetails.mnpRequestedDate = subscriptionDetails.mnpRequestedDate
          ? moment(subscriptionDetails.mnpRequestedDate)
          : undefined;
        subscriptionDetails.createdDate = subscriptionDetails.createdDate ? moment(subscriptionDetails.createdDate) : undefined;
        subscriptionDetails.lastUpdatedDate = subscriptionDetails.lastUpdatedDate ? moment(subscriptionDetails.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
