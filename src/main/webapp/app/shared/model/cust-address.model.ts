import { Moment } from 'moment';
import { ICustomer } from 'app/shared/model/customer.model';
import { AccountType } from 'app/shared/model/enumerations/account-type.model';
import { AddressType } from 'app/shared/model/enumerations/address-type.model';
import { Language } from 'app/shared/model/enumerations/language.model';

export interface ICustAddress {
  id?: string;
  addressId?: number;
  accountId?: string;
  accountType?: AccountType;
  addressType?: AddressType;
  addressLang?: Language;
  formattedAddress?: boolean;
  room?: string;
  floor?: string;
  block?: string;
  building?: string;
  streetEstate?: string;
  district?: string;
  region?: string;
  address1?: string;
  address2?: string;
  address3?: string;
  address4?: string;
  address5?: string;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  customer?: ICustomer;
}

export class CustAddress implements ICustAddress {
  constructor(
    public id?: string,
    public addressId?: number,
    public accountId?: string,
    public accountType?: AccountType,
    public addressType?: AddressType,
    public addressLang?: Language,
    public formattedAddress?: boolean,
    public room?: string,
    public floor?: string,
    public block?: string,
    public building?: string,
    public streetEstate?: string,
    public district?: string,
    public region?: string,
    public address1?: string,
    public address2?: string,
    public address3?: string,
    public address4?: string,
    public address5?: string,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string,
    public customer?: ICustomer
  ) {
    this.formattedAddress = this.formattedAddress || false;
  }
}
