import { Moment } from 'moment';

export interface ICommMediaType {
  id?: string;
  optoutMediaId?: string;
  mediaType?: string;
  mediaTypeDesc?: string;
  startDate?: Moment;
  endDate?: Moment;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
}

export class CommMediaType implements ICommMediaType {
  constructor(
    public id?: string,
    public optoutMediaId?: string,
    public mediaType?: string,
    public mediaTypeDesc?: string,
    public startDate?: Moment,
    public endDate?: Moment,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string
  ) {}
}
