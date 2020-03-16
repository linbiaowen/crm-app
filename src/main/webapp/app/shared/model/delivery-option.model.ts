import { Moment } from 'moment';
import { DeliverOptions } from 'app/shared/model/enumerations/deliver-options.model';

export interface IDeliveryOption {
  id?: string;
  deliveryOption?: DeliverOptions;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
}

export class DeliveryOption implements IDeliveryOption {
  constructor(
    public id?: string,
    public deliveryOption?: DeliverOptions,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string
  ) {}
}
