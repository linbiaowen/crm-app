import { Moment } from 'moment';
import { IProductSpecification } from 'app/shared/model/product-specification.model';

export interface IProductData {
  id?: string;
  dataId?: string;
  productSpecId?: number;
  unit?: string;
  volume?: number;
  dataSlab?: string;
  dataSpeedCategory?: string;
  specicalPackType?: string;
  dataServiceType?: string;
  roamingRegions?: string;
  roamingCountries?: string;
  roamingDayPassType?: string;
  roamingPackValidPeriodType?: string;
  roamingPackPeriod?: number;
  roamingPackTerm?: string;
  minTransferQuota?: number;
  maxTransferQuota?: number;
  minRetentionQuota?: number;
  minTpTransferQuota?: number;
  maxTpTransferQuota?: number;
  minTpRetentionQuota?: number;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  productSpec?: IProductSpecification;
}

export class ProductData implements IProductData {
  constructor(
    public id?: string,
    public dataId?: string,
    public productSpecId?: number,
    public unit?: string,
    public volume?: number,
    public dataSlab?: string,
    public dataSpeedCategory?: string,
    public specicalPackType?: string,
    public dataServiceType?: string,
    public roamingRegions?: string,
    public roamingCountries?: string,
    public roamingDayPassType?: string,
    public roamingPackValidPeriodType?: string,
    public roamingPackPeriod?: number,
    public roamingPackTerm?: string,
    public minTransferQuota?: number,
    public maxTransferQuota?: number,
    public minRetentionQuota?: number,
    public minTpTransferQuota?: number,
    public maxTpTransferQuota?: number,
    public minTpRetentionQuota?: number,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string,
    public productSpec?: IProductSpecification
  ) {}
}
