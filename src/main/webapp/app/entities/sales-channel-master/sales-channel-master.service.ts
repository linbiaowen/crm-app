import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISalesChannelMaster } from 'app/shared/model/sales-channel-master.model';

type EntityResponseType = HttpResponse<ISalesChannelMaster>;
type EntityArrayResponseType = HttpResponse<ISalesChannelMaster[]>;

@Injectable({ providedIn: 'root' })
export class SalesChannelMasterService {
  public resourceUrl = SERVER_API_URL + 'api/sales-channel-masters';

  constructor(protected http: HttpClient) {}

  create(salesChannelMaster: ISalesChannelMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(salesChannelMaster);
    return this.http
      .post<ISalesChannelMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(salesChannelMaster: ISalesChannelMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(salesChannelMaster);
    return this.http
      .put<ISalesChannelMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<ISalesChannelMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISalesChannelMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(salesChannelMaster: ISalesChannelMaster): ISalesChannelMaster {
    const copy: ISalesChannelMaster = Object.assign({}, salesChannelMaster, {
      startDate: salesChannelMaster.startDate && salesChannelMaster.startDate.isValid() ? salesChannelMaster.startDate.toJSON() : undefined,
      endDate: salesChannelMaster.endDate && salesChannelMaster.endDate.isValid() ? salesChannelMaster.endDate.toJSON() : undefined,
      createdDate:
        salesChannelMaster.createdDate && salesChannelMaster.createdDate.isValid() ? salesChannelMaster.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        salesChannelMaster.lastUpdatedDate && salesChannelMaster.lastUpdatedDate.isValid()
          ? salesChannelMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((salesChannelMaster: ISalesChannelMaster) => {
        salesChannelMaster.startDate = salesChannelMaster.startDate ? moment(salesChannelMaster.startDate) : undefined;
        salesChannelMaster.endDate = salesChannelMaster.endDate ? moment(salesChannelMaster.endDate) : undefined;
        salesChannelMaster.createdDate = salesChannelMaster.createdDate ? moment(salesChannelMaster.createdDate) : undefined;
        salesChannelMaster.lastUpdatedDate = salesChannelMaster.lastUpdatedDate ? moment(salesChannelMaster.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
