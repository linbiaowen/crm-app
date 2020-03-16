import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISupremeMaster } from 'app/shared/model/supreme-master.model';

type EntityResponseType = HttpResponse<ISupremeMaster>;
type EntityArrayResponseType = HttpResponse<ISupremeMaster[]>;

@Injectable({ providedIn: 'root' })
export class SupremeMasterService {
  public resourceUrl = SERVER_API_URL + 'api/supreme-masters';

  constructor(protected http: HttpClient) {}

  create(supremeMaster: ISupremeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(supremeMaster);
    return this.http
      .post<ISupremeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(supremeMaster: ISupremeMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(supremeMaster);
    return this.http
      .put<ISupremeMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<ISupremeMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISupremeMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(supremeMaster: ISupremeMaster): ISupremeMaster {
    const copy: ISupremeMaster = Object.assign({}, supremeMaster, {
      startDate: supremeMaster.startDate && supremeMaster.startDate.isValid() ? supremeMaster.startDate.toJSON() : undefined,
      endDate: supremeMaster.endDate && supremeMaster.endDate.isValid() ? supremeMaster.endDate.toJSON() : undefined,
      createdDate: supremeMaster.createdDate && supremeMaster.createdDate.isValid() ? supremeMaster.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        supremeMaster.lastUpdatedDate && supremeMaster.lastUpdatedDate.isValid() ? supremeMaster.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((supremeMaster: ISupremeMaster) => {
        supremeMaster.startDate = supremeMaster.startDate ? moment(supremeMaster.startDate) : undefined;
        supremeMaster.endDate = supremeMaster.endDate ? moment(supremeMaster.endDate) : undefined;
        supremeMaster.createdDate = supremeMaster.createdDate ? moment(supremeMaster.createdDate) : undefined;
        supremeMaster.lastUpdatedDate = supremeMaster.lastUpdatedDate ? moment(supremeMaster.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
