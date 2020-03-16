import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IModelCategory } from 'app/shared/model/model-category.model';

type EntityResponseType = HttpResponse<IModelCategory>;
type EntityArrayResponseType = HttpResponse<IModelCategory[]>;

@Injectable({ providedIn: 'root' })
export class ModelCategoryService {
  public resourceUrl = SERVER_API_URL + 'api/model-categories';

  constructor(protected http: HttpClient) {}

  create(modelCategory: IModelCategory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(modelCategory);
    return this.http
      .post<IModelCategory>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(modelCategory: IModelCategory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(modelCategory);
    return this.http
      .put<IModelCategory>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IModelCategory>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IModelCategory[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(modelCategory: IModelCategory): IModelCategory {
    const copy: IModelCategory = Object.assign({}, modelCategory, {
      createdDate: modelCategory.createdDate && modelCategory.createdDate.isValid() ? modelCategory.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        modelCategory.lastUpdatedDate && modelCategory.lastUpdatedDate.isValid() ? modelCategory.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((modelCategory: IModelCategory) => {
        modelCategory.createdDate = modelCategory.createdDate ? moment(modelCategory.createdDate) : undefined;
        modelCategory.lastUpdatedDate = modelCategory.lastUpdatedDate ? moment(modelCategory.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
