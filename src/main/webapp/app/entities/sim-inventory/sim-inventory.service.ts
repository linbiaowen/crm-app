import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISimInventory } from 'app/shared/model/sim-inventory.model';

type EntityResponseType = HttpResponse<ISimInventory>;
type EntityArrayResponseType = HttpResponse<ISimInventory[]>;

@Injectable({ providedIn: 'root' })
export class SimInventoryService {
  public resourceUrl = SERVER_API_URL + 'api/sim-inventories';

  constructor(protected http: HttpClient) {}

  create(simInventory: ISimInventory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(simInventory);
    return this.http
      .post<ISimInventory>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(simInventory: ISimInventory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(simInventory);
    return this.http
      .put<ISimInventory>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<ISimInventory>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISimInventory[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(simInventory: ISimInventory): ISimInventory {
    const copy: ISimInventory = Object.assign({}, simInventory, {
      createdDate: simInventory.createdDate && simInventory.createdDate.isValid() ? simInventory.createdDate.toJSON() : undefined,
      lastUpdatedDate:
        simInventory.lastUpdatedDate && simInventory.lastUpdatedDate.isValid() ? simInventory.lastUpdatedDate.toJSON() : undefined
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
      res.body.forEach((simInventory: ISimInventory) => {
        simInventory.createdDate = simInventory.createdDate ? moment(simInventory.createdDate) : undefined;
        simInventory.lastUpdatedDate = simInventory.lastUpdatedDate ? moment(simInventory.lastUpdatedDate) : undefined;
      });
    }
    return res;
  }
}
