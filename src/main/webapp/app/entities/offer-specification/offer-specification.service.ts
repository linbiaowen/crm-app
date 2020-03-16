import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOfferSpecification } from 'app/shared/model/offer-specification.model';

type EntityResponseType = HttpResponse<IOfferSpecification>;
type EntityArrayResponseType = HttpResponse<IOfferSpecification[]>;

@Injectable({ providedIn: 'root' })
export class OfferSpecificationService {
  public resourceUrl = SERVER_API_URL + 'api/offer-specifications';

  constructor(protected http: HttpClient) {}

  create(offerSpecification: IOfferSpecification): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(offerSpecification);
    return this.http
      .post<IOfferSpecification>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(offerSpecification: IOfferSpecification): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(offerSpecification);
    return this.http
      .put<IOfferSpecification>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IOfferSpecification>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOfferSpecification[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(offerSpecification: IOfferSpecification): IOfferSpecification {
    const copy: IOfferSpecification = Object.assign({}, offerSpecification, {
      startDate: offerSpecification.startDate && offerSpecification.startDate.isValid() ? offerSpecification.startDate.toJSON() : undefined,
      endDate: offerSpecification.endDate && offerSpecification.endDate.isValid() ? offerSpecification.endDate.toJSON() : undefined,
      allowedActivationStartDate:
        offerSpecification.allowedActivationStartDate && offerSpecification.allowedActivationStartDate.isValid()
          ? offerSpecification.allowedActivationStartDate.toJSON()
          : undefined,
      allowedActivationEndDate:
        offerSpecification.allowedActivationEndDate && offerSpecification.allowedActivationEndDate.isValid()
          ? offerSpecification.allowedActivationEndDate.toJSON()
          : undefined,
      createdDate:
        offerSpecification.createdDate && offerSpecification.createdDate.isValid() ? offerSpecification.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        offerSpecification.lastUpdatedDate && offerSpecification.lastUpdatedDate.isValid()
          ? offerSpecification.lastUpdatedDate.toJSON()
          : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.startDate = res.body.startDate ? moment(res.body.startDate) : undefined;
      res.body.endDate = res.body.endDate ? moment(res.body.endDate) : undefined;
      res.body.allowedActivationStartDate = res.body.allowedActivationStartDate ? moment(res.body.allowedActivationStartDate) : undefined;
      res.body.allowedActivationEndDate = res.body.allowedActivationEndDate ? moment(res.body.allowedActivationEndDate) : undefined;
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.lastUpdatedDate = res.body.lastUpdatedDate ? moment(res.body.lastUpdatedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((offerSpecification: IOfferSpecification) => {
        offerSpecification.startDate = offerSpecification.startDate ? moment(offerSpecification.startDate) : undefined;
        offerSpecification.endDate = offerSpecification.endDate ? moment(offerSpecification.endDate) : undefined;
        offerSpecification.allowedActivationStartDate = offerSpecification.allowedActivationStartDate
          ? moment(offerSpecification.allowedActivationStartDate)
          : undefined;
        offerSpecification.allowedActivationEndDate = offerSpecification.allowedActivationEndDate
          ? moment(offerSpecification.allowedActivationEndDate)
          : undefined;
        offerSpecification.createdDate = offerSpecification.createdDate ? moment(offerSpecification.createdDate) : undefined;
        offerSpecification.lastUpdatedDate = offerSpecification.lastUpdatedDate ? moment(offerSpecification.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
