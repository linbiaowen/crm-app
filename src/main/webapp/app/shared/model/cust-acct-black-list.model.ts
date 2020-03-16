import { Moment } from 'moment';

export interface ICustAcctBlackList {
  id?: string;
  blackListId?: number;
  idType?: string;
  idNumber?: string;
  blackListCode?: string;
  startDate?: Moment;
  endDate?: Moment;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
}

export class CustAcctBlackList implements ICustAcctBlackList {
  constructor(
    public id?: string,
    public blackListId?: number,
    public idType?: string,
    public idNumber?: string,
    public blackListCode?: string,
    public startDate?: Moment,
    public endDate?: Moment,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string
  ) {}
}
