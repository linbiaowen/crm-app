import { Moment } from 'moment';
import { RecordStatus } from 'app/shared/model/enumerations/record-status.model';

export interface IModel {
  id?: string;
  modelCode?: string;
  modelGroup?: string;
  status?: RecordStatus;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
}

export class Model implements IModel {
  constructor(
    public id?: string,
    public modelCode?: string,
    public modelGroup?: string,
    public status?: RecordStatus,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string
  ) {}
}
