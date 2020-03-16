import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBlackListMaster } from 'app/shared/model/black-list-master.model';

type EntityResponseType = HttpResponse<IBlackListMaster>;
type EntityArrayResponseType = HttpResponse<IBlackListMaster[]>;

@Injectable({ providedIn: 'root' })
export class BlackListMasterService {
  public resourceUrl = SERVER_API_URL + 'api/black-list-masters';

  constructor(protected http: HttpClient) {}

  create(blackListMaster: IBlackListMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(blackListMaster);
    return this.http
      .post<IBlackListMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(blackListMaster: IBlackListMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(blackListMaster);
    return this.http
      .put<IBlackListMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IBlackListMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBlackListMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(blackListMaster: IBlackListMaster): IBlackListMaster {
    const copy: IBlackListMaster = Object.assign({}, blackListMaster, {
      startDate: blackListMaster.startDate && blackListMaster.startDate.isValid() ? blackListMaster.startDate.toJSON() : undefined,
      endDate: blackListMaster.endDate && blackListMaster.endDate.isValid() ? blackListMaster.endDate.toJSON() : undefined,
      createdDate: blackListMaster.createdDate && blackListMaster.createdDate.isValid() ? blackListMaster.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        blackListMaster.lastUpdatedDate && blackListMaster.lastUpdatedDate.isValid() ? blackListMaster.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((blackListMaster: IBlackListMaster) => {
        blackListMaster.startDate = blackListMaster.startDate ? moment(blackListMaster.startDate) : undefined;
        blackListMaster.endDate = blackListMaster.endDate ? moment(blackListMaster.endDate) : undefined;
        blackListMaster.createdDate = blackListMaster.createdDate ? moment(blackListMaster.createdDate) : undefined;
        blackListMaster.lastUpdatedDate = blackListMaster.lastUpdatedDate ? moment(blackListMaster.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
