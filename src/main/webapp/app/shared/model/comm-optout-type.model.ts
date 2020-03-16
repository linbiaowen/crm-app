import { Moment } from 'moment';

export interface ICommOptoutType {
  id?: string;
  optoutTypeId?: string;
  optoutType?: string;
  optoutTypeDesc?: string;
  startDate?: Moment;
  endDate?: Moment;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
}

export class CommOptoutType implements ICommOptoutType {
  constructor(
    public id?: string,
    public optoutTypeId?: string,
    public optoutType?: string,
    public optoutTypeDesc?: string,
    public startDate?: Moment,
    public endDate?: Moment,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string
  ) {}
}
