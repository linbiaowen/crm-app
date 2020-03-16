import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IResourceSpecification } from 'app/shared/model/resource-specification.model';

type EntityResponseType = HttpResponse<IResourceSpecification>;
type EntityArrayResponseType = HttpResponse<IResourceSpecification[]>;

@Injectable({ providedIn: 'root' })
export class ResourceSpecificationService {
  public resourceUrl = SERVER_API_URL + 'api/resource-specifications';

  constructor(protected http: HttpClient) {}

  create(resourceSpecification: IResourceSpecification): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(resourceSpecification);
    return this.http
      .post<IResourceSpecification>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(resourceSpecification: IResourceSpecification): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(resourceSpecification);
    return this.http
      .put<IResourceSpecification>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IResourceSpecification>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IResourceSpecification[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(resourceSpecification: IResourceSpecification): IResourceSpecification {
    const copy: IResourceSpecification = Object.assign({}, resourceSpecification, {
      createdDate:
        resourceSpecification.createdDate && resourceSpecification.createdDate.isValid()
          ? resourceSpecification.createdDate.toJSON()
          : undefined,
      lastUpdatedDate:
        resourceSpecification.lastUpdatedDate && resourceSpecification.lastUpdatedDate.isValid()
          ? resourceSpecification.lastUpdatedDate.toJSON()
          : undefined
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
      res.body.forEach((resourceSpecification: IResourceSpecification) => {
        resourceSpecification.createdDate = resourceSpecification.createdDate ? moment(resourceSpecification.createdDate) : undefined;
        resourceSpecification.lastUpdatedDate = resourceSpecification.lastUpdatedDate
          ? moment(resourceSpecification.lastUpdatedDate)
          : undefined;
      });
    }
    return res;
  }
}
