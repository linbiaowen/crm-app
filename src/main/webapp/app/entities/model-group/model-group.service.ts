import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IModelGroup } from 'app/shared/model/model-group.model';

type EntityResponseType = HttpResponse<IModelGroup>;
type EntityArrayResponseType = HttpResponse<IModelGroup[]>;

@Injectable({ providedIn: 'root' })
export class ModelGroupService {
  public resourceUrl = SERVER_API_URL + 'api/model-groups';

  constructor(protected http: HttpClient) {}

  create(modelGroup: IModelGroup): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(modelGroup);
    return this.http
      .post<IModelGroup>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(modelGroup: IModelGroup): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(modelGroup);
    return this.http
      .put<IModelGroup>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IModelGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IModelGroup[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(modelGroup: IModelGroup): IModelGroup {
    const copy: IModelGroup = Object.assign({}, modelGroup, {
      createdDate: modelGroup.createdDate && modelGroup.createdDate.isValid() ? modelGroup.createdDate.toJSON() : undefined,
      lastUpdatedDate: modelGroup.lastUpdatedDate && modelGroup.lastUpdatedDate.isValid() ? modelGroup.lastUpdatedDate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.lastUpdatedDate = res.body.lastUpdatedDate ? moment(res.body.lastUpdatedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((modelGroup: IModelGroup) => {
        modelGroup.createdDate = modelGroup.createdDate ? moment(modelGroup.createdDate) : undefined;
        modelGroup.lastUpdatedDate = modelGroup.lastUpdatedDate ? moment(modelGroup.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
