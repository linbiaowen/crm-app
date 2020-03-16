import { Moment } from 'moment';
import { RecordStatus } from 'app/shared/model/enumerations/record-status.model';

export interface ISubsPurchaseControl {
  id?: string;
  subscriptionId?: string;
  serviceType?: string;
  purchaseControlFlag?: string;
  status?: RecordStatus;
  startDate?: Moment;
  endDate?: Moment;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
}

export class SubsPurchaseControl implements ISubsPurchaseControl {
  constructor(
    public id?: string,
    public subscriptionId?: string,
    public serviceType?: string,
    public purchaseControlFlag?: string,
    public status?: RecordStatus,
    public startDate?: Moment,
    public endDate?: Moment,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string
  ) {}
}
