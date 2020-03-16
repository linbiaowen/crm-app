import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGroupEndReason } from 'app/shared/model/group-end-reason.model';

type EntityResponseType = HttpResponse<IGroupEndReason>;
type EntityArrayResponseType = HttpResponse<IGroupEndReason[]>;

@Injectable({ providedIn: 'root' })
export class GroupEndReasonService {
  public resourceUrl = SERVER_API_URL + 'api/group-end-reasons';

  constructor(protected http: HttpClient) {}

  create(groupEndReason: IGroupEndReason): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(groupEndReason);
    return this.http
      .post<IGroupEndReason>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(groupEndReason: IGroupEndReason): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(groupEndReason);
    return this.http
      .put<IGroupEndReason>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IGroupEndReason>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IGroupEndReason[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(groupEndReason: IGroupEndReason): IGroupEndReason {
    const copy: IGroupEndReason = Object.assign({}, groupEndReason, {
      startDate: groupEndReason.startDate && groupEndReason.startDate.isValid() ? groupEndReason.startDate.toJSON() : undefined,
      endDate: groupEndReason.endDate && groupEndReason.endDate.isValid() ? groupEndReason.endDate.toJSON() : undefined,
      createdDate: groupEndReason.createdDate && groupEndReason.createdDate.isValid() ? groupEndReason.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        groupEndReason.lastUpdatedDate && groupEndReason.lastUpdatedDate.isValid() ? groupEndReason.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((groupEndReason: IGroupEndReason) => {
        groupEndReason.startDate = groupEndReason.startDate ? moment(groupEndReason.startDate) : undefined;
        groupEndReason.endDate = groupEndReason.endDate ? moment(groupEndReason.endDate) : undefined;
        groupEndReason.createdDate = groupEndReason.createdDate ? moment(groupEndReason.createdDate) : undefined;
        groupEndReason.lastUpdatedDate = groupEndReason.lastUpdatedDate ? moment(groupEndReason.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
