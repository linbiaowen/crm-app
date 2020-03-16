import { Moment } from 'moment';
import { ICustomer } from 'app/shared/model/customer.model';
import { ICustSubscription } from 'app/shared/model/cust-subscription.model';

export interface ICustCommOptoutMaster {
  id?: string;
  optoutId?: string;
  custAcctId?: string;
  subscriptionId?: string;
  primaryMobNbr?: string;
  optoutTypeId?: string;
  optoutMediaId?: string;
  optoutStatus?: string;
  optoutStartDate?: Moment;
  optoutEndDate?: Moment;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  customer?: ICustomer;
  custSubscription?: ICustSubscription;
}

export class CustCommOptoutMaster implements ICustCommOptoutMaster {
  constructor(
    public id?: string,
    public optoutId?: string,
    public custAcctId?: string,
    public subscriptionId?: string,
    public primaryMobNbr?: string,
    public optoutTypeId?: string,
    public optoutMediaId?: string,
    public optoutStatus?: string,
    public optoutStartDate?: Moment,
    public optoutEndDate?: Moment,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string,
    public customer?: ICustomer,
    public custSubscription?: ICustSubscription
  ) {}
}
