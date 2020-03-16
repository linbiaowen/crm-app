import { Moment } from 'moment';

export interface IModelCategory {
  id?: string;
  modelCate?: string;
  parentModelCate?: string;
  modelCateDesc?: string;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
}

export class ModelCategory implements IModelCategory {
  constructor(
    public id?: string,
    public modelCate?: string,
    public parentModelCate?: string,
    public modelCateDesc?: string,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string
  ) {}
}
