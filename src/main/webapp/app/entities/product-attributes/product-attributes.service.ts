import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProductAttributes } from 'app/shared/model/product-attributes.model';

type EntityResponseType = HttpResponse<IProductAttributes>;
type EntityArrayResponseType = HttpResponse<IProductAttributes[]>;

@Injectable({ providedIn: 'root' })
export class ProductAttributesService {
  public resourceUrl = SERVER_API_URL + 'api/product-attributes';

  constructor(protected http: HttpClient) {}

  create(productAttributes: IProductAttributes): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(productAttributes);
    return this.http
      .post<IProductAttributes>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(productAttributes: IProductAttributes): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(productAttributes);
    return this.http
      .put<IProductAttributes>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IProductAttributes>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IProductAttributes[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(productAttributes: IProductAttributes): IProductAttributes {
    const copy: IProductAttributes = Object.assign({}, productAttributes, {
      createdDate:
        productAttributes.createdDate && productAttributes.createdDate.isValid() ? productAttributes.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        productAttributes.lastUpdatedDate && productAttributes.lastUpdatedDate.isValid()
          ? productAttributes.lastUpdatedDate.toJSON()
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
      res.body.forEach((productAttributes: IProductAttributes) => {
        productAttributes.createdDate = productAttributes.createdDate ? moment(productAttributes.createdDate) : undefined;
        productAttributes.lastUpdatedDate = productAttributes.lastUpdatedDate ? moment(productAttributes.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
