import { Moment } from 'moment';
import { ICustSubscription } from 'app/shared/model/cust-subscription.model';
import { Language } from 'app/shared/model/enumerations/language.model';

export interface ISubscriptionDetails {
  id?: string;
  subsDetailId?: number;
  subscriptionId?: string;
  startDate?: Moment;
  endDate?: Moment;
  orderId?: string;
  ssaNbr?: string;
  primaryMsisdn?: string;
  iccid?: string;
  imsi?: string;
  mnpRequestedDate?: Moment;
  lang?: Language;
  baseOfferId?: string;
  baseOfferName?: string;
  matrixxCatalogId?: string;
  matrixxResourceId?: string;
  matrixxObjectId?: string;
  salesChannel?: string;
  advancePaymentMonths?: number;
  offerPrice?: number;
  networkType?: string;
  serviceType?: string;
  offerPlanCode?: string;
  serviceInPerson?: string;
  fcmToken?: string;
  remarks?: string;
  cdVersion?: string;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  custSubscription?: ICustSubscription;
}

export class SubscriptionDetails implements ISubscriptionDetails {
  constructor(
    public id?: string,
    public subsDetailId?: number,
    public subscriptionId?: string,
    public startDate?: Moment,
    public endDate?: Moment,
    public orderId?: string,
    public ssaNbr?: string,
    public primaryMsisdn?: string,
    public iccid?: string,
    public imsi?: string,
    public mnpRequestedDate?: Moment,
    public lang?: Language,
    public baseOfferId?: string,
    public baseOfferName?: string,
    public matrixxCatalogId?: string,
    public matrixxResourceId?: string,
    public matrixxObjectId?: string,
    public salesChannel?: string,
    public advancePaymentMonths?: number,
    public offerPrice?: number,
    public networkType?: string,
    public serviceType?: string,
    public offerPlanCode?: string,
    public serviceInPerson?: string,
    public fcmToken?: string,
    public remarks?: string,
    public cdVersion?: string,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string,
    public custSubscription?: ICustSubscription
  ) {}
}
