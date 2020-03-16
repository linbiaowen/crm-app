import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDeliveryOption } from 'app/shared/model/delivery-option.model';

type EntityResponseType = HttpResponse<IDeliveryOption>;
type EntityArrayResponseType = HttpResponse<IDeliveryOption[]>;

@Injectable({ providedIn: 'root' })
export class DeliveryOptionService {
  public resourceUrl = SERVER_API_URL + 'api/delivery-options';

  constructor(protected http: HttpClient) {}

  create(deliveryOption: IDeliveryOption): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(deliveryOption);
    return this.http
      .post<IDeliveryOption>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(deliveryOption: IDeliveryOption): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(deliveryOption);
    return this.http
      .put<IDeliveryOption>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IDeliveryOption>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDeliveryOption[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(deliveryOption: IDeliveryOption): IDeliveryOption {
    const copy: IDeliveryOption = Object.assign({}, deliveryOption, {
      createdDate: deliveryOption.createdDate && deliveryOption.createdDate.isValid() ? deliveryOption.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        deliveryOption.lastUpdatedDate && deliveryOption.lastUpdatedDate.isValid() ? deliveryOption.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((deliveryOption: IDeliveryOption) => {
        deliveryOption.createdDate = deliveryOption.createdDate ? moment(deliveryOption.createdDate) : undefined;
        deliveryOption.lastUpdatedDate = deliveryOption.lastUpdatedDate ? moment(deliveryOption.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
