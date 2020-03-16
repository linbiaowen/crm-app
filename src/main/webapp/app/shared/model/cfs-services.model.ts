import { Moment } from 'moment';
import { ICfsServiceSpec } from 'app/shared/model/cfs-service-spec.model';

export interface ICfsServices {
  id?: string;
  serviceId?: string;
  serviceName?: string;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  cfsServiceSpec?: ICfsServiceSpec;
}

export class CfsServices implements ICfsServices {
  constructor(
    public id?: string,
    public serviceId?: string,
    public serviceName?: string,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string,
    public cfsServiceSpec?: ICfsServiceSpec
  ) {}
}
