import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICustContact } from 'app/shared/model/cust-contact.model';

type EntityResponseType = HttpResponse<ICustContact>;
type EntityArrayResponseType = HttpResponse<ICustContact[]>;

@Injectable({ providedIn: 'root' })
export class CustContactService {
  public resourceUrl = SERVER_API_URL + 'api/cust-contacts';

  constructor(protected http: HttpClient) {}

  create(custContact: ICustContact): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(custContact);
    return this.http
      .post<ICustContact>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(custContact: ICustContact): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(custContact);
    return this.http
      .put<ICustContact>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<ICustContact>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICustContact[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(custContact: ICustContact): ICustContact {
    const copy: ICustContact = Object.assign({}, custContact, {
      createdDate: custContact.createdDate && custContact.createdDate.isValid() ? custContact.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        custContact.lastUpdatedDate && custContact.lastUpdatedDate.isValid() ? custContact.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((custContact: ICustContact) => {
        custContact.createdDate = custContact.createdDate ? moment(custContact.createdDate) : undefined;
        custContact.lastUpdatedDate = custContact.lastUpdatedDate ? moment(custContact.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
