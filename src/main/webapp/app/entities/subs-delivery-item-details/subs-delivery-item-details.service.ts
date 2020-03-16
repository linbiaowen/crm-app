import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISubsDeliveryItemDetails } from 'app/shared/model/subs-delivery-item-details.model';

type EntityResponseType = HttpResponse<ISubsDeliveryItemDetails>;
type EntityArrayResponseType = HttpResponse<ISubsDeliveryItemDetails[]>;

@Injectable({ providedIn: 'root' })
export class SubsDeliveryItemDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/subs-delivery-item-details';

  constructor(protected http: HttpClient) {}

  create(subsDeliveryItemDetails: ISubsDeliveryItemDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(subsDeliveryItemDetails);
    return this.http
      .post<ISubsDeliveryItemDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(subsDeliveryItemDetails: ISubsDeliveryItemDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(subsDeliveryItemDetails);
    return this.http
      .put<ISubsDeliveryItemDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<ISubsDeliveryItemDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISubsDeliveryItemDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(subsDeliveryItemDetails: ISubsDeliveryItemDetails): ISubsDeliveryItemDetails {
    const copy: ISubsDeliveryItemDetails = Object.assign({}, subsDeliveryItemDetails, {
      createdDate:
        subsDeliveryItemDetails.createdDate && subsDeliveryItemDetails.createdDate.isValid()
          ? subsDeliveryItemDetails.createdDate.toJSON()
          : undefined,
      lastUpdatedDate:
        subsDeliveryItemDetails.lastUpdatedDate && subsDeliveryItemDetails.lastUpdatedDate.isValid()
          ? subsDeliveryItemDetails.lastUpdatedDate.toJSON()
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
      res.body.forEach((subsDeliveryItemDetails: ISubsDeliveryItemDetails) => {
        subsDeliveryItemDetails.createdDate = subsDeliveryItemDetails.createdDate ? moment(subsDeliveryItemDetails.createdDate) : undefined;
        subsDeliveryItemDetails.lastUpdatedDate = subsDeliveryItemDetails.lastUpdatedDate
          ? moment(subsDeliveryItemDetails.lastUpdatedDate)
          : undefined;
      });
    }
    return res;
  }
}
