import { Moment } from 'moment';

export interface ISalesChannelMaster {
  id?: string;
  salesChannel?: string;
  salesChannelDesc?: string;
  startDate?: Moment;
  endDate?: Moment;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
}

export class SalesChannelMaster implements ISalesChannelMaster {
  constructor(
    public id?: string,
    public salesChannel?: string,
    public salesChannelDesc?: string,
    public startDate?: Moment,
    public endDate?: Moment,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string
  ) {}
}
