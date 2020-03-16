import { Moment } from 'moment';
import { ICfsServiceSpec } from 'app/shared/model/cfs-service-spec.model';

export interface IDataServiceSpec {
  id?: string;
  dataSpecId?: number;
  serviceSpecId?: string;
  maxEntitlement?: string;
  maxAccessSpeed?: string;
  throttledSpeed?: string;
  upstreamSpeed?: string;
  downstreamSpeed?: string;
  socialSites?: string;
  entertainmentPackOptions?: string;
  roamingDataSpeed?: string;
  roamingDataVolume?: string;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  cfsServices?: ICfsServiceSpec;
}

export class DataServiceSpec implements IDataServiceSpec {
  constructor(
    public id?: string,
    public dataSpecId?: number,
    public serviceSpecId?: string,
    public maxEntitlement?: string,
    public maxAccessSpeed?: string,
    public throttledSpeed?: string,
    public upstreamSpeed?: string,
    public downstreamSpeed?: string,
    public socialSites?: string,
    public entertainmentPackOptions?: string,
    public roamingDataSpeed?: string,
    public roamingDataVolume?: string,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string,
    public cfsServices?: ICfsServiceSpec
  ) {}
}
