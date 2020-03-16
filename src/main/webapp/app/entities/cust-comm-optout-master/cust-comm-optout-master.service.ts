import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICustCommOptoutMaster } from 'app/shared/model/cust-comm-optout-master.model';

type EntityResponseType = HttpResponse<ICustCommOptoutMaster>;
type EntityArrayResponseType = HttpResponse<ICustCommOptoutMaster[]>;

@Injectable({ providedIn: 'root' })
export class CustCommOptoutMasterService {
  public resourceUrl = SERVER_API_URL + 'api/cust-comm-optout-masters';

  constructor(protected http: HttpClient) {}

  create(custCommOptoutMaster: ICustCommOptoutMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(custCommOptoutMaster);
    return this.http
      .post<ICustCommOptoutMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(custCommOptoutMaster: ICustCommOptoutMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(custCommOptoutMaster);
    return this.http
      .put<ICustCommOptoutMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<ICustCommOptoutMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICustCommOptoutMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(custCommOptoutMaster: ICustCommOptoutMaster): ICustCommOptoutMaster {
    const copy: ICustCommOptoutMaster = Object.assign({}, custCommOptoutMaster, {
      optoutStartDate:
        custCommOptoutMaster.optoutStartDate && custCommOptoutMaster.optoutStartDate.isValid()
          ? custCommOptoutMaster.optoutStartDate.toJSON()
          : undefined,
      optoutEndDate:
        custCommOptoutMaster.optoutEndDate && custCommOptoutMaster.optoutEndDate.isValid()
          ? custCommOptoutMaster.optoutEndDate.toJSON()
          : undefined,
      createdDate:
        custCommOptoutMaster.createdDate && custCommOptoutMaster.createdDate.isValid()
          ? custCommOptoutMaster.createdDate.toJSON()
          : undefined,
      lastUpdatedDate:
        custCommOptoutMaster.lastUpdatedDate && custCommOptoutMaster.lastUpdatedDate.isValid()
          ? custCommOptoutMaster.lastUpdatedDate.toJSON()
          : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.optoutStartDate = res.body.optoutStartDate ? moment(res.body.optoutStartDate) : undefined;
      res.body.optoutEndDate = res.body.optoutEndDate ? moment(res.body.optoutEndDate) : undefined;
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.lastUpdatedDate = res.body.lastUpdatedDate ? moment(res.body.lastUpdatedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((custCommOptoutMaster: ICustCommOptoutMaster) => {
        custCommOptoutMaster.optoutStartDate = custCommOptoutMaster.optoutStartDate
          ? moment(custCommOptoutMaster.optoutStartDate)
          : undefined;
        custCommOptoutMaster.optoutEndDate = custCommOptoutMaster.optoutEndDate ? moment(custCommOptoutMaster.optoutEndDate) : undefined;
        custCommOptoutMaster.createdDate = custCommOptoutMaster.createdDate ? moment(custCommOptoutMaster.createdDate) : undefined;
        custCommOptoutMaster.lastUpdatedDate = custCommOptoutMaster.lastUpdatedDate
          ? moment(custCommOptoutMaster.lastUpdatedDate)
          : undefined;
      });
    }
    return res;
  }
}
