import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICustomer } from 'app/shared/model/customer.model';

type EntityResponseType = HttpResponse<ICustomer>;
type EntityArrayResponseType = HttpResponse<ICustomer[]>;

@Injectable({ providedIn: 'root' })
export class CustomerService {
  public resourceUrl = SERVER_API_URL + 'api/customers';

  constructor(protected http: HttpClient) {}

  create(customer: ICustomer): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(customer);
    return this.http
      .post<ICustomer>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(customer: ICustomer): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(customer);
    return this.http
      .put<ICustomer>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<ICustomer>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICustomer[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(customer: ICustomer): ICustomer {
    const copy: ICustomer = Object.assign({}, customer, {
      acctStartDate: customer.acctStartDate && customer.acctStartDate.isValid() ? customer.acctStartDate.toJSON() : undefined,
      acctEndDate: customer.acctEndDate && customer.acctEndDate.isValid() ? customer.acctEndDate.toJSON() : undefined,
      birthDate: customer.birthDate && customer.birthDate.isValid() ? customer.birthDate.format(DATE_FORMAT) : undefined,
      createdDate: customer.createdDate && customer.createdDate.isValid() ? customer.createdDate.toJSON() : undefined,
      lastUpdatedDate: customer.lastUpdatedDate && customer.lastUpdatedDate.isValid() ? customer.lastUpdatedDate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.acctStartDate = res.body.acctStartDate ? moment(res.body.acctStartDate) : undefined;
      res.body.acctEndDate = res.body.acctEndDate ? moment(res.body.acctEndDate) : undefined;
      res.body.birthDate = res.body.birthDate ? moment(res.body.birthDate) : undefined;
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.lastUpdatedDate = res.body.lastUpdatedDate ? moment(res.body.lastUpdatedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((customer: ICustomer) => {
        customer.acctStartDate = customer.acctStartDate ? moment(customer.acctStartDate) : undefined;
        customer.acctEndDate = customer.acctEndDate ? moment(customer.acctEndDate) : undefined;
        customer.birthDate = customer.birthDate ? moment(customer.birthDate) : undefined;
        customer.createdDate = customer.createdDate ? moment(customer.createdDate) : undefined;
        customer.lastUpdatedDate = customer.lastUpdatedDate ? moment(customer.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
