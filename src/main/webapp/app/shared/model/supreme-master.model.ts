import { Moment } from 'moment';
import { ICustSubscription } from 'app/shared/model/cust-subscription.model';
import { MemberShipServiceType } from 'app/shared/model/enumerations/member-ship-service-type.model';

export interface ISupremeMaster {
  id?: string;
  supremeSeqId?: number;
  subscriptionId?: string;
  msisdn?: string;
  startDate?: Moment;
  endDate?: Moment;
  membershipServiceType?: MemberShipServiceType;
  peCode?: string;
  personalExecDirectLine?: string;
  personalExecName?: string;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  custSubscription?: ICustSubscription;
}

export class SupremeMaster implements ISupremeMaster {
  constructor(
    public id?: string,
    public supremeSeqId?: number,
    public subscriptionId?: string,
    public msisdn?: string,
    public startDate?: Moment,
    public endDate?: Moment,
    public membershipServiceType?: MemberShipServiceType,
    public peCode?: string,
    public personalExecDirectLine?: string,
    public personalExecName?: string,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string,
    public custSubscription?: ICustSubscription
  ) {}
}
