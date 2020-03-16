import { Moment } from 'moment';
import { IProductVoice } from 'app/shared/model/product-voice.model';
import { IProductData } from 'app/shared/model/product-data.model';
import { IProductSms } from 'app/shared/model/product-sms.model';
import { ICfsServiceSpec } from 'app/shared/model/cfs-service-spec.model';
import { IProduct } from 'app/shared/model/product.model';
import { IOffer } from 'app/shared/model/offer.model';
import { ProductSpecType } from 'app/shared/model/enumerations/product-spec-type.model';
import { SkuType } from 'app/shared/model/enumerations/sku-type.model';
import { SimType } from 'app/shared/model/enumerations/sim-type.model';
import { BoxType } from 'app/shared/model/enumerations/box-type.model';

export interface IProductSpecification {
  id?: string;
  productSpecId?: number;
  productId?: string;
  serviceSpecId?: string;
  productSpecType?: ProductSpecType;
  skuType?: SkuType;
  simType?: SimType;
  boxType?: BoxType;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  voice?: IProductVoice;
  data?: IProductData;
  sms?: IProductSms;
  cfsServiceSpecs?: ICfsServiceSpec[];
  product?: IProduct;
  offer?: IOffer;
}

export class ProductSpecification implements IProductSpecification {
  constructor(
    public id?: string,
    public productSpecId?: number,
    public productId?: string,
    public serviceSpecId?: string,
    public productSpecType?: ProductSpecType,
    public skuType?: SkuType,
    public simType?: SimType,
    public boxType?: BoxType,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string,
    public voice?: IProductVoice,
    public data?: IProductData,
    public sms?: IProductSms,
    public cfsServiceSpecs?: ICfsServiceSpec[],
    public product?: IProduct,
    public offer?: IOffer
  ) {}
}
