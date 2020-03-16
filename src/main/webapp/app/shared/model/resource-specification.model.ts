import { Moment } from 'moment';
import { ICfsServiceSpec } from 'app/shared/model/cfs-service-spec.model';
import { ResourceType } from 'app/shared/model/enumerations/resource-type.model';

export interface IResourceSpecification {
  id?: string;
  resourceSpecId?: string;
  resourceType?: ResourceType;
  serviceSpecId?: string;
  rfs?: string;
  rfsParms?: string;
  remarks?: string;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  cfsServiceSpec?: ICfsServiceSpec;
}

export class ResourceSpecification implements IResourceSpecification {
  constructor(
    public id?: string,
    public resourceSpecId?: string,
    public resourceType?: ResourceType,
    public serviceSpecId?: string,
    public rfs?: string,
    public rfsParms?: string,
    public remarks?: string,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string,
    public cfsServiceSpec?: ICfsServiceSpec
  ) {}
}
