import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProductVoice } from 'app/shared/model/product-voice.model';

type EntityResponseType = HttpResponse<IProductVoice>;
type EntityArrayResponseType = HttpResponse<IProductVoice[]>;

@Injectable({ providedIn: 'root' })
export class ProductVoiceService {
  public resourceUrl = SERVER_API_URL + 'api/product-voices';

  constructor(protected http: HttpClient) {}

  create(productVoice: IProductVoice): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(productVoice);
    return this.http
      .post<IProductVoice>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(productVoice: IProductVoice): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(productVoice);
    return this.http
      .put<IProductVoice>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IProductVoice>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IProductVoice[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(productVoice: IProductVoice): IProductVoice {
    const copy: IProductVoice = Object.assign({}, productVoice, {
      createdDate: productVoice.createdDate && productVoice.createdDate.isValid() ? productVoice.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        productVoice.lastUpdatedDate && productVoice.lastUpdatedDate.isValid() ? productVoice.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((productVoice: IProductVoice) => {
        productVoice.createdDate = productVoice.createdDate ? moment(productVoice.createdDate) : undefined;
        productVoice.lastUpdatedDate = productVoice.lastUpdatedDate ? moment(productVoice.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
