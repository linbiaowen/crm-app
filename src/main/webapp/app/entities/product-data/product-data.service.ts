import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProductData } from 'app/shared/model/product-data.model';

type EntityResponseType = HttpResponse<IProductData>;
type EntityArrayResponseType = HttpResponse<IProductData[]>;

@Injectable({ providedIn: 'root' })
export class ProductDataService {
  public resourceUrl = SERVER_API_URL + 'api/product-data';

  constructor(protected http: HttpClient) {}

  create(productData: IProductData): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(productData);
    return this.http
      .post<IProductData>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(productData: IProductData): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(productData);
    return this.http
      .put<IProductData>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IProductData>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IProductData[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(productData: IProductData): IProductData {
    const copy: IProductData = Object.assign({}, productData, {
      createdDate: productData.createdDate && productData.createdDate.isValid() ? productData.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        productData.lastUpdatedDate && productData.lastUpdatedDate.isValid() ? productData.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((productData: IProductData) => {
        productData.createdDate = productData.createdDate ? moment(productData.createdDate) : undefined;
        productData.lastUpdatedDate = productData.lastUpdatedDate ? moment(productData.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
