import { Moment } from 'moment';
import { ISubsItemDelivery } from 'app/shared/model/subs-item-delivery.model';

export interface ISubsDeliveryItemDetails {
  id?: string;
  subscriptionItemId?: number;
  deliveryId?: number;
  productId?: string;
  productName?: string;
  deviceType?: string;
  deviceModel?: string;
  deviceSerialNbr?: string;
  imei?: string;
  productTheme?: string;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  subsItemDelivery?: ISubsItemDelivery;
}

export class SubsDeliveryItemDetails implements ISubsDeliveryItemDetails {
  constructor(
    public id?: string,
    public subscriptionItemId?: number,
    public deliveryId?: number,
    public productId?: string,
    public productName?: string,
    public deviceType?: string,
    public deviceModel?: string,
    public deviceSerialNbr?: string,
    public imei?: string,
    public productTheme?: string,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string,
    public subsItemDelivery?: ISubsItemDelivery
  ) {}
}
