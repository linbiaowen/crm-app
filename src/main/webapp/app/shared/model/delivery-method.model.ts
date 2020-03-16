import { Moment } from 'moment';

export interface IDeliveryMethod {
  id?: string;
  deliveryMethodId?: number;
  deliveryMethod?: string;
  deliveryMethodDesc?: string;
  startDate?: Moment;
  endDate?: Moment;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
}

export class DeliveryMethod implements IDeliveryMethod {
  constructor(
    public id?: string,
    public deliveryMethodId?: number,
    public deliveryMethod?: string,
    public deliveryMethodDesc?: string,
    public startDate?: Moment,
    public endDate?: Moment,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string
  ) {}
}
