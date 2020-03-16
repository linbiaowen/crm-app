import { Moment } from 'moment';
import { SimType } from 'app/shared/model/enumerations/sim-type.model';

export interface IProductSimAttributes {
  id?: string;
  simType?: SimType;
  imsiRangeFrom?: string;
  imsiRangeTo?: string;
  simRepositoryRef?: string;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
}

export class ProductSimAttributes implements IProductSimAttributes {
  constructor(
    public id?: string,
    public simType?: SimType,
    public imsiRangeFrom?: string,
    public imsiRangeTo?: string,
    public simRepositoryRef?: string,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string
  ) {}
}
