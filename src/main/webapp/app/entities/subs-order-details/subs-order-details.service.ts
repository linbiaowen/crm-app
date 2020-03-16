import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISubsOrderDetails } from 'app/shared/model/subs-order-details.model';

type EntityResponseType = HttpResponse<ISubsOrderDetails>;
type EntityArrayResponseType = HttpResponse<ISubsOrderDetails[]>;

@Injectable({ providedIn: 'root' })
export class SubsOrderDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/subs-order-details';

  constructor(protected http: HttpClient) {}

  create(subsOrderDetails: ISubsOrderDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(subsOrderDetails);
    return this.http
      .post<ISubsOrderDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(subsOrderDetails: ISubsOrderDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(subsOrderDetails);
    return this.http
      .put<ISubsOrderDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<ISubsOrderDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISubsOrderDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(subsOrderDetails: ISubsOrderDetails): ISubsOrderDetails {
    const copy: ISubsOrderDetails = Object.assign({}, subsOrderDetails, {
      startDate: subsOrderDetails.startDate && subsOrderDetails.startDate.isValid() ? subsOrderDetails.startDate.toJSON() : undefined,
      endDate: subsOrderDetails.endDate && subsOrderDetails.endDate.isValid() ? subsOrderDetails.endDate.toJSON() : undefined,
      simVerifiedDate:
        subsOrderDetails.simVerifiedDate && subsOrderDetails.simVerifiedDate.isValid()
          ? subsOrderDetails.simVerifiedDate.toJSON()
          : undefined,
      mnpRequestedDate:
        subsOrderDetails.mnpRequestedDate && subsOrderDetails.mnpRequestedDate.isValid()
          ? subsOrderDetails.mnpRequestedDate.toJSON()
          : undefined,
      createdDate:
        subsOrderDetails.createdDate && subsOrderDetails.createdDate.isValid() ? subsOrderDetails.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        subsOrderDetails.lastUpdatedDate && subsOrderDetails.lastUpdatedDate.isValid()
          ? subsOrderDetails.lastUpdatedDate.toJSON()
          : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.startDate = res.body.startDate ? moment(res.body.startDate) : undefined;
      res.body.endDate = res.body.endDate ? moment(res.body.endDate) : undefined;
      res.body.simVerifiedDate = res.body.simVerifiedDate ? moment(res.body.simVerifiedDate) : undefined;
      res.body.mnpRequestedDate = res.body.mnpRequestedDate ? moment(res.body.mnpRequestedDate) : undefined;
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.lastUpdatedDate = res.body.lastUpdatedDate ? moment(res.body.lastUpdatedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((subsOrderDetails: ISubsOrderDetails) => {
        subsOrderDetails.startDate = subsOrderDetails.startDate ? moment(subsOrderDetails.startDate) : undefined;
        subsOrderDetails.endDate = subsOrderDetails.endDate ? moment(subsOrderDetails.endDate) : undefined;
        subsOrderDetails.simVerifiedDate = subsOrderDetails.simVerifiedDate ? moment(subsOrderDetails.simVerifiedDate) : undefined;
        subsOrderDetails.mnpRequestedDate = subsOrderDetails.mnpRequestedDate ? moment(subsOrderDetails.mnpRequestedDate) : undefined;
        subsOrderDetails.createdDate = subsOrderDetails.createdDate ? moment(subsOrderDetails.createdDate) : undefined;
        subsOrderDetails.lastUpdatedDate = subsOrderDetails.lastUpdatedDate ? moment(subsOrderDetails.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
