import { Moment } from 'moment';

export interface IProductBoxType {
  id?: string;
  boxType?: string;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
}

export class ProductBoxType implements IProductBoxType {
  constructor(
    public id?: string,
    public boxType?: string,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string
  ) {}
}
