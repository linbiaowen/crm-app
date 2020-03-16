import { Moment } from 'moment';
import { ISubsOrderDetails } from 'app/shared/model/subs-order-details.model';
import { ISubsItemDelivery } from 'app/shared/model/subs-item-delivery.model';
import { ISubscriptionProvision } from 'app/shared/model/subscription-provision.model';
import { IOrderProcessStatus } from 'app/shared/model/order-process-status.model';
import { ICustomer } from 'app/shared/model/customer.model';
import { ICustSubscription } from 'app/shared/model/cust-subscription.model';
import { OrderType } from 'app/shared/model/enumerations/order-type.model';
import { SubOrderType } from 'app/shared/model/enumerations/sub-order-type.model';
import { OrderNature } from 'app/shared/model/enumerations/order-nature.model';
import { OrderStatus } from 'app/shared/model/enumerations/order-status.model';

export interface IOrderMaster {
  id?: string;
  orderId?: string;
  custAcctId?: string;
  subscriptionId?: string;
  orderType?: OrderType;
  subOrderType?: SubOrderType;
  orderNature?: OrderNature;
  isChangePlan?: boolean;
  orderStatus?: OrderStatus;
  remarks?: string;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  subsOrderDetails?: ISubsOrderDetails[];
  subsItemDeliverys?: ISubsItemDelivery[];
  subscriptionProvisions?: ISubscriptionProvision[];
  orderProcessStatuses?: IOrderProcessStatus[];
  customer?: ICustomer;
  custSubscription?: ICustSubscription;
}

export class OrderMaster implements IOrderMaster {
  constructor(
    public id?: string,
    public orderId?: string,
    public custAcctId?: string,
    public subscriptionId?: string,
    public orderType?: OrderType,
    public subOrderType?: SubOrderType,
    public orderNature?: OrderNature,
    public isChangePlan?: boolean,
    public orderStatus?: OrderStatus,
    public remarks?: string,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string,
    public subsOrderDetails?: ISubsOrderDetails[],
    public subsItemDeliverys?: ISubsItemDelivery[],
    public subscriptionProvisions?: ISubscriptionProvision[],
    public orderProcessStatuses?: IOrderProcessStatus[],
    public customer?: ICustomer,
    public custSubscription?: ICustSubscription
  ) {
    this.isChangePlan = this.isChangePlan || false;
  }
}
