import { Moment } from 'moment';
import { IVoiceServiceSpec } from 'app/shared/model/voice-service-spec.model';
import { IDataServiceSpec } from 'app/shared/model/data-service-spec.model';
import { IResourceSpecification } from 'app/shared/model/resource-specification.model';
import { ICfsServices } from 'app/shared/model/cfs-services.model';
import { IProductSpecification } from 'app/shared/model/product-specification.model';

export interface ICfsServiceSpec {
  id?: string;
  serviceSpecId?: string;
  serviceSpecDesc?: string;
  serviceId?: string;
  voiceSpecId?: number;
  dataSpecId?: number;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  voiceServiceSpec?: IVoiceServiceSpec;
  dataServiceSpec?: IDataServiceSpec;
  resourceSpecifications?: IResourceSpecification[];
  cfsServices?: ICfsServices;
  productSpecification?: IProductSpecification;
}

export class CfsServiceSpec implements ICfsServiceSpec {
  constructor(
    public id?: string,
    public serviceSpecId?: string,
    public serviceSpecDesc?: string,
    public serviceId?: string,
    public voiceSpecId?: number,
    public dataSpecId?: number,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string,
    public voiceServiceSpec?: IVoiceServiceSpec,
    public dataServiceSpec?: IDataServiceSpec,
    public resourceSpecifications?: IResourceSpecification[],
    public cfsServices?: ICfsServices,
    public productSpecification?: IProductSpecification
  ) {}
}
