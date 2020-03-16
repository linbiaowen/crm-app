import { Moment } from 'moment';
import { ICustDocument } from 'app/shared/model/cust-document.model';
import { ICustCommOptoutMaster } from 'app/shared/model/cust-comm-optout-master.model';
import { ICustContact } from 'app/shared/model/cust-contact.model';
import { ICustAddress } from 'app/shared/model/cust-address.model';
import { ISubscriptionGroup } from 'app/shared/model/subscription-group.model';
import { ICustSubscription } from 'app/shared/model/cust-subscription.model';
import { IOrderMaster } from 'app/shared/model/order-master.model';
import { AcctStatus } from 'app/shared/model/enumerations/acct-status.model';
import { Language } from 'app/shared/model/enumerations/language.model';
import { CustomerSegment } from 'app/shared/model/enumerations/customer-segment.model';

export interface ICustomer {
  id?: string;
  custAcctId?: string;
  parentCustAcctId?: string;
  acctStatus?: AcctStatus;
  acctStartDate?: Moment;
  acctEndDate?: Moment;
  cabsAcctId?: string;
  title?: string;
  givenName?: string;
  familyName?: string;
  givenNameChi?: string;
  familyNameChi?: string;
  aliasName?: string;
  gender?: string;
  birthDate?: Moment;
  idType?: string;
  idNumber?: string;
  companyNameEng?: string;
  companyNameChi?: string;
  unlimitedCompany?: boolean;
  lang?: Language;
  selfCareUserId?: string;
  selfCarePassword?: string;
  ivrPin?: string;
  maritialStatus?: string;
  customerSegment?: CustomerSegment;
  customerClass?: string;
  billingAcctId?: string;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  parentCustomer?: ICustomer;
  custDocuments?: ICustDocument[];
  custCommOptouts?: ICustCommOptoutMaster[];
  custContacts?: ICustContact[];
  custAddresses?: ICustAddress[];
  subscriptionGroups?: ISubscriptionGroup[];
  subscriptions?: ICustSubscription[];
  orderMasters?: IOrderMaster[];
}

export class Customer implements ICustomer {
  constructor(
    public id?: string,
    public custAcctId?: string,
    public parentCustAcctId?: string,
    public acctStatus?: AcctStatus,
    public acctStartDate?: Moment,
    public acctEndDate?: Moment,
    public cabsAcctId?: string,
    public title?: string,
    public givenName?: string,
    public familyName?: string,
    public givenNameChi?: string,
    public familyNameChi?: string,
    public aliasName?: string,
    public gender?: string,
    public birthDate?: Moment,
    public idType?: string,
    public idNumber?: string,
    public companyNameEng?: string,
    public companyNameChi?: string,
    public unlimitedCompany?: boolean,
    public lang?: Language,
    public selfCareUserId?: string,
    public selfCarePassword?: string,
    public ivrPin?: string,
    public maritialStatus?: string,
    public customerSegment?: CustomerSegment,
    public customerClass?: string,
    public billingAcctId?: string,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string,
    public parentCustomer?: ICustomer,
    public custDocuments?: ICustDocument[],
    public custCommOptouts?: ICustCommOptoutMaster[],
    public custContacts?: ICustContact[],
    public custAddresses?: ICustAddress[],
    public subscriptionGroups?: ISubscriptionGroup[],
    public subscriptions?: ICustSubscription[],
    public orderMasters?: IOrderMaster[]
  ) {
    this.unlimitedCompany = this.unlimitedCompany || false;
  }
}
