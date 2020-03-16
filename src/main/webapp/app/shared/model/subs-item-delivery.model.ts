import { Moment } from 'moment';
import { ISubsDeliveryItemDetails } from 'app/shared/model/subs-delivery-item-details.model';
import { IOrderMaster } from 'app/shared/model/order-master.model';

export interface ISubsItemDelivery {
  id?: string;
  deliveryId?: number;
  orderId?: string;
  subscriptionId?: string;
  subscriptionItemId?: string;
  deliveryStatus?: string;
  deliveryMethodId?: string;
  deliveryRefCode?: string;
  fileGenerationDate?: Moment;
  fileReceivedDate?: Moment;
  deliveryDate?: Moment;
  remarks?: string;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  subsDeliveryItemDetails?: ISubsDeliveryItemDetails[];
  orderMaster?: IOrderMaster;
}

export class SubsItemDelivery implements ISubsItemDelivery {
  constructor(
    public id?: string,
    public deliveryId?: number,
    public orderId?: string,
    public subscriptionId?: string,
    public subscriptionItemId?: string,
    public deliveryStatus?: string,
    public deliveryMethodId?: string,
    public deliveryRefCode?: string,
    public fileGenerationDate?: Moment,
    public fileReceivedDate?: Moment,
    public deliveryDate?: Moment,
    public remarks?: string,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string,
    public subsDeliveryItemDetails?: ISubsDeliveryItemDetails[],
    public orderMaster?: IOrderMaster
  ) {}
}
