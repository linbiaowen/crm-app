import { Moment } from 'moment';
import { IProductSpecification } from 'app/shared/model/product-specification.model';

export interface IProductVoice {
  id?: string;
  voiceId?: string;
  productSpecId?: number;
  unit?: string;
  volume?: number;
  roamingFlag?: boolean;
  minTransferQuota?: number;
  maxTransferQuota?: number;
  minRetentionQuota?: number;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  productSpec?: IProductSpecification;
}

export class ProductVoice implements IProductVoice {
  constructor(
    public id?: string,
    public voiceId?: string,
    public productSpecId?: number,
    public unit?: string,
    public volume?: number,
    public roamingFlag?: boolean,
    public minTransferQuota?: number,
    public maxTransferQuota?: number,
    public minRetentionQuota?: number,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string,
    public productSpec?: IProductSpecification
  ) {
    this.roamingFlag = this.roamingFlag || false;
  }
}
