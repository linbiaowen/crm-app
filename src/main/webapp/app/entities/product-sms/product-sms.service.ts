import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProductSms } from 'app/shared/model/product-sms.model';

type EntityResponseType = HttpResponse<IProductSms>;
type EntityArrayResponseType = HttpResponse<IProductSms[]>;

@Injectable({ providedIn: 'root' })
export class ProductSmsService {
  public resourceUrl = SERVER_API_URL + 'api/product-sms';

  constructor(protected http: HttpClient) {}

  create(productSms: IProductSms): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(productSms);
    return this.http
      .post<IProductSms>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(productSms: IProductSms): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(productSms);
    return this.http
      .put<IProductSms>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IProductSms>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IProductSms[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(productSms: IProductSms): IProductSms {
    const copy: IProductSms = Object.assign({}, productSms, {
      createdDate: productSms.createdDate && productSms.createdDate.isValid() ? productSms.createdDate.toJSON() : undefined,
      lastUpdatedDate: productSms.lastUpdatedDate && productSms.lastUpdatedDate.isValid() ? productSms.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((productSms: IProductSms) => {
        productSms.createdDate = productSms.createdDate ? moment(productSms.createdDate) : undefined;
        productSms.lastUpdatedDate = productSms.lastUpdatedDate ? moment(productSms.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
