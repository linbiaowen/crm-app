import { Moment } from 'moment';

export interface ICustDocDataStore {
  id?: string;
  docDataStoreId?: number;
  documentDataContentType?: string;
  documentData?: any;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
}

export class CustDocDataStore implements ICustDocDataStore {
  constructor(
    public id?: string,
    public docDataStoreId?: number,
    public documentDataContentType?: string,
    public documentData?: any,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string
  ) {}
}
