import { Moment } from 'moment';
import { ICustSubscription } from 'app/shared/model/cust-subscription.model';

export interface ISubscriptionProduct {
  id?: string;
  productSubsriptionSeqId?: number;
  orderId?: string;
  subscriptionId?: string;
  productId?: string;
  productName?: string;
  activationDate?: Moment;
  endDate?: Moment;
  secondMsisdn?: string;
  secondImsi?: string;
  quantity?: number;
  terminationReasonCode?: string;
  offerId?: string;
  offerName?: string;
  offerType?: string;
  matrixxCatalogId?: string;
  matrixxResourceId?: string;
  matrixxObjectId?: string;
  salesChannel?: string;
  contractId?: string;
  autoRenewal?: boolean;
  autoPay?: boolean;
  remarks?: string;
  vendorProvisionInd?: boolean;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  custSubscription?: ICustSubscription;
}

export class SubscriptionProduct implements ISubscriptionProduct {
  constructor(
    public id?: string,
    public productSubsriptionSeqId?: number,
    public orderId?: string,
    public subscriptionId?: string,
    public productId?: string,
    public productName?: string,
    public activationDate?: Moment,
    public endDate?: Moment,
    public secondMsisdn?: string,
    public secondImsi?: string,
    public quantity?: number,
    public terminationReasonCode?: string,
    public offerId?: string,
    public offerName?: string,
    public offerType?: string,
    public matrixxCatalogId?: string,
    public matrixxResourceId?: string,
    public matrixxObjectId?: string,
    public salesChannel?: string,
    public contractId?: string,
    public autoRenewal?: boolean,
    public autoPay?: boolean,
    public remarks?: string,
    public vendorProvisionInd?: boolean,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string,
    public custSubscription?: ICustSubscription
  ) {
    this.autoRenewal = this.autoRenewal || false;
    this.autoPay = this.autoPay || false;
    this.vendorProvisionInd = this.vendorProvisionInd || false;
  }
}
