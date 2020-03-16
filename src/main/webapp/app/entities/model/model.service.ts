import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IModel } from 'app/shared/model/model.model';

type EntityResponseType = HttpResponse<IModel>;
type EntityArrayResponseType = HttpResponse<IModel[]>;

@Injectable({ providedIn: 'root' })
export class ModelService {
  public resourceUrl = SERVER_API_URL + 'api/models';

  constructor(protected http: HttpClient) {}

  create(model: IModel): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(model);
    return this.http
      .post<IModel>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(model: IModel): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(model);
    return this.http
      .put<IModel>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IModel>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IModel[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(model: IModel): IModel {
    const copy: IModel = Object.assign({}, model, {
      createdDate: model.createdDate && model.createdDate.isValid() ? model.createdDate.toJSON() : undefined,
      lastUpdatedDate: model.lastUpdatedDate && model.lastUpdatedDate.isValid() ? model.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((model: IModel) => {
        model.createdDate = model.createdDate ? moment(model.createdDate) : undefined;
        model.lastUpdatedDate = model.lastUpdatedDate ? moment(model.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
