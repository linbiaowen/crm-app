import { Moment } from 'moment';

export interface IGroupType {
  id?: string;
  groupTypeId?: number;
  groupType?: string;
  startDate?: Moment;
  endDate?: Moment;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
}

export class GroupType implements IGroupType {
  constructor(
    public id?: string,
    public groupTypeId?: number,
    public groupType?: string,
    public startDate?: Moment,
    public endDate?: Moment,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string
  ) {}
}
