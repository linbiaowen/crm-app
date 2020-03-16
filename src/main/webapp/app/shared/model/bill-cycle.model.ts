import { Moment } from 'moment';

export interface IBillCycle {
  id?: string;
  billCycleId?: number;
  billCycleDesc?: string;
  billCycle?: number;
  billFrequency?: string;
  billCycleStartDate?: Moment;
  billCycleEndDate?: Moment;
  dueDateOffset?: number;
  directDebitProcessDay?: number;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
}

export class BillCycle implements IBillCycle {
  constructor(
    public id?: string,
    public billCycleId?: number,
    public billCycleDesc?: string,
    public billCycle?: number,
    public billFrequency?: string,
    public billCycleStartDate?: Moment,
    public billCycleEndDate?: Moment,
    public dueDateOffset?: number,
    public directDebitProcessDay?: number,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string
  ) {}
}
