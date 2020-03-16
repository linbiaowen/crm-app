import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProductBoxType } from 'app/shared/model/product-box-type.model';

type EntityResponseType = HttpResponse<IProductBoxType>;
type EntityArrayResponseType = HttpResponse<IProductBoxType[]>;

@Injectable({ providedIn: 'root' })
export class ProductBoxTypeService {
  public resourceUrl = SERVER_API_URL + 'api/product-box-types';

  constructor(protected http: HttpClient) {}

  create(productBoxType: IProductBoxType): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(productBoxType);
    return this.http
      .post<IProductBoxType>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(productBoxType: IProductBoxType): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(productBoxType);
    return this.http
      .put<IProductBoxType>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IProductBoxType>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IProductBoxType[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(productBoxType: IProductBoxType): IProductBoxType {
    const copy: IProductBoxType = Object.assign({}, productBoxType, {
      createdDate: productBoxType.createdDate && productBoxType.createdDate.isValid() ? productBoxType.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        productBoxType.lastUpdatedDate && productBoxType.lastUpdatedDate.isValid() ? productBoxType.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((productBoxType: IProductBoxType) => {
        productBoxType.createdDate = productBoxType.createdDate ? moment(productBoxType.createdDate) : undefined;
        productBoxType.lastUpdatedDate = productBoxType.lastUpdatedDate ? moment(productBoxType.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
