import { Moment } from 'moment';
import { OrderStatus } from 'app/shared/model/enumerations/order-status.model';
import { ProcessStatus } from 'app/shared/model/enumerations/process-status.model';

export interface IOrderProcessStatusHistory {
  id?: string;
  orderId?: string;
  entryOrderStatus?: OrderStatus;
  exitOrderStatus?: OrderStatus;
  statusUpdatedDate?: Moment;
  processName?: string;
  status?: ProcessStatus;
  remarks?: string;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
}

export class OrderProcessStatusHistory implements IOrderProcessStatusHistory {
  constructor(
    public id?: string,
    public orderId?: string,
    public entryOrderStatus?: OrderStatus,
    public exitOrderStatus?: OrderStatus,
    public statusUpdatedDate?: Moment,
    public processName?: string,
    public status?: ProcessStatus,
    public remarks?: string,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string
  ) {}
}
