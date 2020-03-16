import { Moment } from 'moment';
import { SkuType } from 'app/shared/model/enumerations/sku-type.model';

export interface IProductAttributes {
  id?: string;
  skuType?: SkuType;
  shippable?: boolean;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
}

export class ProductAttributes implements IProductAttributes {
  constructor(
    public id?: string,
    public skuType?: SkuType,
    public shippable?: boolean,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string
  ) {
    this.shippable = this.shippable || false;
  }
}
