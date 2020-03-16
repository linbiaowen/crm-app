import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGroupMembers } from 'app/shared/model/group-members.model';

type EntityResponseType = HttpResponse<IGroupMembers>;
type EntityArrayResponseType = HttpResponse<IGroupMembers[]>;

@Injectable({ providedIn: 'root' })
export class GroupMembersService {
  public resourceUrl = SERVER_API_URL + 'api/group-members';

  constructor(protected http: HttpClient) {}

  create(groupMembers: IGroupMembers): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(groupMembers);
    return this.http
      .post<IGroupMembers>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(groupMembers: IGroupMembers): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(groupMembers);
    return this.http
      .put<IGroupMembers>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IGroupMembers>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IGroupMembers[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(groupMembers: IGroupMembers): IGroupMembers {
    const copy: IGroupMembers = Object.assign({}, groupMembers, {
      startDate: groupMembers.startDate && groupMembers.startDate.isValid() ? groupMembers.startDate.toJSON() : undefined,
      endDate: groupMembers.endDate && groupMembers.endDate.isValid() ? groupMembers.endDate.toJSON() : undefined,
      createdDate: groupMembers.createdDate && groupMembers.createdDate.isValid() ? groupMembers.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        groupMembers.lastUpdatedDate && groupMembers.lastUpdatedDate.isValid() ? groupMembers.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((groupMembers: IGroupMembers) => {
        groupMembers.startDate = groupMembers.startDate ? moment(groupMembers.startDate) : undefined;
        groupMembers.endDate = groupMembers.endDate ? moment(groupMembers.endDate) : undefined;
        groupMembers.createdDate = groupMembers.createdDate ? moment(groupMembers.createdDate) : undefined;
        groupMembers.lastUpdatedDate = groupMembers.lastUpdatedDate ? moment(groupMembers.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
