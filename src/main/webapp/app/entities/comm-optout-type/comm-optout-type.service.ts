import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICommOptoutType } from 'app/shared/model/comm-optout-type.model';

type EntityResponseType = HttpResponse<ICommOptoutType>;
type EntityArrayResponseType = HttpResponse<ICommOptoutType[]>;

@Injectable({ providedIn: 'root' })
export class CommOptoutTypeService {
  public resourceUrl = SERVER_API_URL + 'api/comm-optout-types';

  constructor(protected http: HttpClient) {}

  create(commOptoutType: ICommOptoutType): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(commOptoutType);
    return this.http
      .post<ICommOptoutType>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(commOptoutType: ICommOptoutType): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(commOptoutType);
    return this.http
      .put<ICommOptoutType>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<ICommOptoutType>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICommOptoutType[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(commOptoutType: ICommOptoutType): ICommOptoutType {
    const copy: ICommOptoutType = Object.assign({}, commOptoutType, {
      startDate: commOptoutType.startDate && commOptoutType.startDate.isValid() ? commOptoutType.startDate.toJSON() : undefined,
      endDate: commOptoutType.endDate && commOptoutType.endDate.isValid() ? commOptoutType.endDate.toJSON() : undefined,
      createdDate: commOptoutType.createdDate && commOptoutType.createdDate.isValid() ? commOptoutType.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        commOptoutType.lastUpdatedDate && commOptoutType.lastUpdatedDate.isValid() ? commOptoutType.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((commOptoutType: ICommOptoutType) => {
        commOptoutType.startDate = commOptoutType.startDate ? moment(commOptoutType.startDate) : undefined;
        commOptoutType.endDate = commOptoutType.endDate ? moment(commOptoutType.endDate) : undefined;
        commOptoutType.createdDate = commOptoutType.createdDate ? moment(commOptoutType.createdDate) : undefined;
        commOptoutType.lastUpdatedDate = commOptoutType.lastUpdatedDate ? moment(commOptoutType.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
