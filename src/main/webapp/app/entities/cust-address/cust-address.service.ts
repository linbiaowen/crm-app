import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICustAddress } from 'app/shared/model/cust-address.model';

type EntityResponseType = HttpResponse<ICustAddress>;
type EntityArrayResponseType = HttpResponse<ICustAddress[]>;

@Injectable({ providedIn: 'root' })
export class CustAddressService {
  public resourceUrl = SERVER_API_URL + 'api/cust-addresses';

  constructor(protected http: HttpClient) {}

  create(custAddress: ICustAddress): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(custAddress);
    return this.http
      .post<ICustAddress>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(custAddress: ICustAddress): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(custAddress);
    return this.http
      .put<ICustAddress>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<ICustAddress>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICustAddress[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(custAddress: ICustAddress): ICustAddress {
    const copy: ICustAddress = Object.assign({}, custAddress, {
      createdDate: custAddress.createdDate && custAddress.createdDate.isValid() ? custAddress.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        custAddress.lastUpdatedDate && custAddress.lastUpdatedDate.isValid() ? custAddress.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((custAddress: ICustAddress) => {
        custAddress.createdDate = custAddress.createdDate ? moment(custAddress.createdDate) : undefined;
        custAddress.lastUpdatedDate = custAddress.lastUpdatedDate ? moment(custAddress.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
