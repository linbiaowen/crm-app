import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICommMediaType } from 'app/shared/model/comm-media-type.model';

type EntityResponseType = HttpResponse<ICommMediaType>;
type EntityArrayResponseType = HttpResponse<ICommMediaType[]>;

@Injectable({ providedIn: 'root' })
export class CommMediaTypeService {
  public resourceUrl = SERVER_API_URL + 'api/comm-media-types';

  constructor(protected http: HttpClient) {}

  create(commMediaType: ICommMediaType): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(commMediaType);
    return this.http
      .post<ICommMediaType>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(commMediaType: ICommMediaType): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(commMediaType);
    return this.http
      .put<ICommMediaType>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<ICommMediaType>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICommMediaType[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(commMediaType: ICommMediaType): ICommMediaType {
    const copy: ICommMediaType = Object.assign({}, commMediaType, {
      startDate: commMediaType.startDate && commMediaType.startDate.isValid() ? commMediaType.startDate.toJSON() : undefined,
      endDate: commMediaType.endDate && commMediaType.endDate.isValid() ? commMediaType.endDate.toJSON() : undefined,
      createdDate: commMediaType.createdDate && commMediaType.createdDate.isValid() ? commMediaType.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        commMediaType.lastUpdatedDate && commMediaType.lastUpdatedDate.isValid() ? commMediaType.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((commMediaType: ICommMediaType) => {
        commMediaType.startDate = commMediaType.startDate ? moment(commMediaType.startDate) : undefined;
        commMediaType.endDate = commMediaType.endDate ? moment(commMediaType.endDate) : undefined;
        commMediaType.createdDate = commMediaType.createdDate ? moment(commMediaType.createdDate) : undefined;
        commMediaType.lastUpdatedDate = commMediaType.lastUpdatedDate ? moment(commMediaType.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
