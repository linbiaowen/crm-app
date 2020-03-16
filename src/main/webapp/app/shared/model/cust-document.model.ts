import { Moment } from 'moment';
import { ICustDocDataStore } from 'app/shared/model/cust-doc-data-store.model';
import { ICustomer } from 'app/shared/model/customer.model';
import { ICustSubscription } from 'app/shared/model/cust-subscription.model';
import { DocType } from 'app/shared/model/enumerations/doc-type.model';

export interface ICustDocument {
  id?: string;
  custDocId?: string;
  custAcctId?: string;
  subscriptionId?: string;
  docType?: DocType;
  docIdNumber?: string;
  docDataStoreId?: number;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  docDataStore?: ICustDocDataStore;
  customer?: ICustomer;
  custSubscription?: ICustSubscription;
}

export class CustDocument implements ICustDocument {
  constructor(
    public id?: string,
    public custDocId?: string,
    public custAcctId?: string,
    public subscriptionId?: string,
    public docType?: DocType,
    public docIdNumber?: string,
    public docDataStoreId?: number,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string,
    public docDataStore?: ICustDocDataStore,
    public customer?: ICustomer,
    public custSubscription?: ICustSubscription
  ) {}
}
