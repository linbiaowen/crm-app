import { Moment } from 'moment';
import { RecordStatus } from 'app/shared/model/enumerations/record-status.model';

export interface ISimInventory {
  id?: string;
  iccid?: string;
  imsi?: string;
  ki?: string;
  k4sno?: string;
  opsno?: string;
  offerId?: string;
  offerName?: string;
  status?: RecordStatus;
  offerValidFromDate?: string;
  offerValidToDate?: string;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
}

export class SimInventory implements ISimInventory {
  constructor(
    public id?: string,
    public iccid?: string,
    public imsi?: string,
    public ki?: string,
    public k4sno?: string,
    public opsno?: string,
    public offerId?: string,
    public offerName?: string,
    public status?: RecordStatus,
    public offerValidFromDate?: string,
    public offerValidToDate?: string,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string
  ) {}
}
