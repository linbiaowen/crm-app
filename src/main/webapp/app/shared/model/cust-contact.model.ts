import { Moment } from 'moment';
import { ICustomer } from 'app/shared/model/customer.model';
import { AccountType } from 'app/shared/model/enumerations/account-type.model';
import { ContactType } from 'app/shared/model/enumerations/contact-type.model';
import { RecordStatus } from 'app/shared/model/enumerations/record-status.model';

export interface ICustContact {
  id?: string;
  contactId?: number;
  accountId?: string;
  accountType?: AccountType;
  contactType?: ContactType;
  contactPerson?: string;
  contactPhone?: string;
  contactEmail?: string;
  status?: RecordStatus;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  customer?: ICustomer;
}

export class CustContact implements ICustContact {
  constructor(
    public id?: string,
    public contactId?: number,
    public accountId?: string,
    public accountType?: AccountType,
    public contactType?: ContactType,
    public contactPerson?: string,
    public contactPhone?: string,
    public contactEmail?: string,
    public status?: RecordStatus,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string,
    public customer?: ICustomer
  ) {}
}
