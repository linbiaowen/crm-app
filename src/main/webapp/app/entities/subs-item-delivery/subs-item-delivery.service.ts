import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISubsItemDelivery } from 'app/shared/model/subs-item-delivery.model';

type EntityResponseType = HttpResponse<ISubsItemDelivery>;
type EntityArrayResponseType = HttpResponse<ISubsItemDelivery[]>;

@Injectable({ providedIn: 'root' })
export class SubsItemDeliveryService {
  public resourceUrl = SERVER_API_URL + 'api/subs-item-deliveries';

  constructor(protected http: HttpClient) {}

  create(subsItemDelivery: ISubsItemDelivery): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(subsItemDelivery);
    return this.http
      .post<ISubsItemDelivery>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(subsItemDelivery: ISubsItemDelivery): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(subsItemDelivery);
    return this.http
      .put<ISubsItemDelivery>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<ISubsItemDelivery>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISubsItemDelivery[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(subsItemDelivery: ISubsItemDelivery): ISubsItemDelivery {
    const copy: ISubsItemDelivery = Object.assign({}, subsItemDelivery, {
      fileGenerationDate:
        subsItemDelivery.fileGenerationDate && subsItemDelivery.fileGenerationDate.isValid()
          ? subsItemDelivery.fileGenerationDate.toJSON()
          : undefined,
      fileReceivedDate:
        subsItemDelivery.fileReceivedDate && subsItemDelivery.fileReceivedDate.isValid()
          ? subsItemDelivery.fileReceivedDate.toJSON()
          : undefined,
      deliveryDate:
        subsItemDelivery.deliveryDate && subsItemDelivery.deliveryDate.isValid() ? subsItemDelivery.deliveryDate.toJSON() : undefined,
      createdDate:
        subsItemDelivery.createdDate && subsItemDelivery.createdDate.isValid() ? subsItemDelivery.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        subsItemDelivery.lastUpdatedDate && subsItemDelivery.lastUpdatedDate.isValid()
          ? subsItemDelivery.lastUpdatedDate.toJSON()
          : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fileGenerationDate = res.body.fileGenerationDate ? moment(res.body.fileGenerationDate) : undefined;
      res.body.fileReceivedDate = res.body.fileReceivedDate ? moment(res.body.fileReceivedDate) : undefined;
      res.body.deliveryDate = res.body.deliveryDate ? moment(res.body.deliveryDate) : undefined;
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.lastUpdatedDate = res.body.lastUpdatedDate ? moment(res.body.lastUpdatedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((subsItemDelivery: ISubsItemDelivery) => {
        subsItemDelivery.fileGenerationDate = subsItemDelivery.fileGenerationDate ? moment(subsItemDelivery.fileGenerationDate) : undefined;
        subsItemDelivery.fileReceivedDate = subsItemDelivery.fileReceivedDate ? moment(subsItemDelivery.fileReceivedDate) : undefined;
        subsItemDelivery.deliveryDate = subsItemDelivery.deliveryDate ? moment(subsItemDelivery.deliveryDate) : undefined;
        subsItemDelivery.createdDate = subsItemDelivery.createdDate ? moment(subsItemDelivery.createdDate) : undefined;
        subsItemDelivery.lastUpdatedDate = subsItemDelivery.lastUpdatedDate ? moment(subsItemDelivery.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
