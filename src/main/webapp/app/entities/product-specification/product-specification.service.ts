import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProductSpecification } from 'app/shared/model/product-specification.model';

type EntityResponseType = HttpResponse<IProductSpecification>;
type EntityArrayResponseType = HttpResponse<IProductSpecification[]>;

@Injectable({ providedIn: 'root' })
export class ProductSpecificationService {
  public resourceUrl = SERVER_API_URL + 'api/product-specifications';

  constructor(protected http: HttpClient) {}

  create(productSpecification: IProductSpecification): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(productSpecification);
    return this.http
      .post<IProductSpecification>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(productSpecification: IProductSpecification): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(productSpecification);
    return this.http
      .put<IProductSpecification>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IProductSpecification>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IProductSpecification[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(productSpecification: IProductSpecification): IProductSpecification {
    const copy: IProductSpecification = Object.assign({}, productSpecification, {
      createdDate:
        productSpecification.createdDate && productSpecification.createdDate.isValid()
          ? productSpecification.createdDate.toJSON()
          : undefined,
      lastUpdatedDate:
        productSpecification.lastUpdatedDate && productSpecification.lastUpdatedDate.isValid()
          ? productSpecification.lastUpdatedDate.toJSON()
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
      res.body.forEach((productSpecification: IProductSpecification) => {
        productSpecification.createdDate = productSpecification.createdDate ? moment(productSpecification.createdDate) : undefined;
        productSpecification.lastUpdatedDate = productSpecification.lastUpdatedDate
          ? moment(productSpecification.lastUpdatedDate)
          : undefined;
      });
    }
    return res;
  }
}
