import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICustDocument } from 'app/shared/model/cust-document.model';

type EntityResponseType = HttpResponse<ICustDocument>;
type EntityArrayResponseType = HttpResponse<ICustDocument[]>;

@Injectable({ providedIn: 'root' })
export class CustDocumentService {
  public resourceUrl = SERVER_API_URL + 'api/cust-documents';

  constructor(protected http: HttpClient) {}

  create(custDocument: ICustDocument): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(custDocument);
    return this.http
      .post<ICustDocument>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(custDocument: ICustDocument): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(custDocument);
    return this.http
      .put<ICustDocument>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<ICustDocument>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICustDocument[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(custDocument: ICustDocument): ICustDocument {
    const copy: ICustDocument = Object.assign({}, custDocument, {
      createdDate: custDocument.createdDate && custDocument.createdDate.isValid() ? custDocument.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        custDocument.lastUpdatedDate && custDocument.lastUpdatedDate.isValid() ? custDocument.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((custDocument: ICustDocument) => {
        custDocument.createdDate = custDocument.createdDate ? moment(custDocument.createdDate) : undefined;
        custDocument.lastUpdatedDate = custDocument.lastUpdatedDate ? moment(custDocument.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
