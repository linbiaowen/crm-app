import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGroupType } from 'app/shared/model/group-type.model';

type EntityResponseType = HttpResponse<IGroupType>;
type EntityArrayResponseType = HttpResponse<IGroupType[]>;

@Injectable({ providedIn: 'root' })
export class GroupTypeService {
  public resourceUrl = SERVER_API_URL + 'api/group-types';

  constructor(protected http: HttpClient) {}

  create(groupType: IGroupType): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(groupType);
    return this.http
      .post<IGroupType>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(groupType: IGroupType): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(groupType);
    return this.http
      .put<IGroupType>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IGroupType>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IGroupType[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(groupType: IGroupType): IGroupType {
    const copy: IGroupType = Object.assign({}, groupType, {
      startDate: groupType.startDate && groupType.startDate.isValid() ? groupType.startDate.toJSON() : undefined,
      endDate: groupType.endDate && groupType.endDate.isValid() ? groupType.endDate.toJSON() : undefined,
      createdDate: groupType.createdDate && groupType.createdDate.isValid() ? groupType.createdDate.toJSON() : undefined,
      lastUpdatedDate: groupType.lastUpdatedDate && groupType.lastUpdatedDate.isValid() ? groupType.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((groupType: IGroupType) => {
        groupType.startDate = groupType.startDate ? moment(groupType.startDate) : undefined;
        groupType.endDate = groupType.endDate ? moment(groupType.endDate) : undefined;
        groupType.createdDate = groupType.createdDate ? moment(groupType.createdDate) : undefined;
        groupType.lastUpdatedDate = groupType.lastUpdatedDate ? moment(groupType.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
