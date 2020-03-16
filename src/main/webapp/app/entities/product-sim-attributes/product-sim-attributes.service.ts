import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProductSimAttributes } from 'app/shared/model/product-sim-attributes.model';

type EntityResponseType = HttpResponse<IProductSimAttributes>;
type EntityArrayResponseType = HttpResponse<IProductSimAttributes[]>;

@Injectable({ providedIn: 'root' })
export class ProductSimAttributesService {
  public resourceUrl = SERVER_API_URL + 'api/product-sim-attributes';

  constructor(protected http: HttpClient) {}

  create(productSimAttributes: IProductSimAttributes): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(productSimAttributes);
    return this.http
      .post<IProductSimAttributes>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(productSimAttributes: IProductSimAttributes): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(productSimAttributes);
    return this.http
      .put<IProductSimAttributes>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IProductSimAttributes>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IProductSimAttributes[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(productSimAttributes: IProductSimAttributes): IProductSimAttributes {
    const copy: IProductSimAttributes = Object.assign({}, productSimAttributes, {
      createdDate:
        productSimAttributes.createdDate && productSimAttributes.createdDate.isValid()
          ? productSimAttributes.createdDate.toJSON()
          : undefined,
      lastUpdatedDate:
        productSimAttributes.lastUpdatedDate && productSimAttributes.lastUpdatedDate.isValid()
          ? productSimAttributes.lastUpdatedDate.toJSON()
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
      res.body.forEach((productSimAttributes: IProductSimAttributes) => {
        productSimAttributes.createdDate = productSimAttributes.createdDate ? moment(productSimAttributes.createdDate) : undefined;
        productSimAttributes.lastUpdatedDate = productSimAttributes.lastUpdatedDate
          ? moment(productSimAttributes.lastUpdatedDate)
          : undefined;
      });
    }
    return res;
  }
}
