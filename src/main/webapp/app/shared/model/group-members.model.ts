import { Moment } from 'moment';
import { ISubscriptionGroup } from 'app/shared/model/subscription-group.model';
import { GroupRole } from 'app/shared/model/enumerations/group-role.model';

export interface IGroupMembers {
  id?: string;
  groupId?: number;
  subscriptionId?: string;
  msisdn?: string;
  groupRole?: GroupRole;
  endReasonCode?: string;
  remarks?: string;
  startDate?: Moment;
  endDate?: Moment;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  subscriptionGroup?: ISubscriptionGroup;
}

export class GroupMembers implements IGroupMembers {
  constructor(
    public id?: string,
    public groupId?: number,
    public subscriptionId?: string,
    public msisdn?: string,
    public groupRole?: GroupRole,
    public endReasonCode?: string,
    public remarks?: string,
    public startDate?: Moment,
    public endDate?: Moment,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string,
    public subscriptionGroup?: ISubscriptionGroup
  ) {}
}
