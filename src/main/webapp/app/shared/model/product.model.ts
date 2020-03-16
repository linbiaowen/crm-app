import { Moment } from 'moment';
import { IProductSpecification } from 'app/shared/model/product-specification.model';
import { ProductCate } from 'app/shared/model/enumerations/product-cate.model';
import { ProductNature } from 'app/shared/model/enumerations/product-nature.model';
import { ProductFamily } from 'app/shared/model/enumerations/product-family.model';
import { ProductType } from 'app/shared/model/enumerations/product-type.model';

export interface IProduct {
  id?: string;
  productId?: string;
  productName?: string;
  productNameChi?: string;
  productDesc?: string;
  productDescChi?: string;
  productCate?: ProductCate;
  productNature?: ProductNature;
  productFamily?: ProductFamily;
  productType?: ProductType;
  modelCode?: string;
  startDate?: Moment;
  endDate?: Moment;
  independentlyOrderable?: boolean;
  networkProvisionRequired?: boolean;
  changeEntitlementAllowed?: boolean;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  productSpec?: IProductSpecification;
}

export class Product implements IProduct {
  constructor(
    public id?: string,
    public productId?: string,
    public productName?: string,
    public productNameChi?: string,
    public productDesc?: string,
    public productDescChi?: string,
    public productCate?: ProductCate,
    public productNature?: ProductNature,
    public productFamily?: ProductFamily,
    public productType?: ProductType,
    public modelCode?: string,
    public startDate?: Moment,
    public endDate?: Moment,
    public independentlyOrderable?: boolean,
    public networkProvisionRequired?: boolean,
    public changeEntitlementAllowed?: boolean,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string,
    public productSpec?: IProductSpecification
  ) {
    this.independentlyOrderable = this.independentlyOrderable || false;
    this.networkProvisionRequired = this.networkProvisionRequired || false;
    this.changeEntitlementAllowed = this.changeEntitlementAllowed || false;
  }
}
