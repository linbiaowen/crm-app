import { Moment } from 'moment';
import { ICfsServiceSpec } from 'app/shared/model/cfs-service-spec.model';

export interface IVoiceServiceSpec {
  id?: string;
  voiceSpecId?: number;
  serviceSpecId?: string;
  roamingIncomingEnabled?: boolean;
  roamingOutgoingEnabled?: boolean;
  iddEnabled?: boolean;
  iddOptions?: string;
  callForwardEnabled?: boolean;
  callWaitingEnabled?: boolean;
  clipEnabled?: boolean;
  callBarringEnabled?: boolean;
  mvrsEnabled?: boolean;
  specialCallServices?: string;
  callRoutingSupported?: boolean;
  prbtSupported?: boolean;
  hrbtSupported?: boolean;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  cfsServices?: ICfsServiceSpec;
}

export class VoiceServiceSpec implements IVoiceServiceSpec {
  constructor(
    public id?: string,
    public voiceSpecId?: number,
    public serviceSpecId?: string,
    public roamingIncomingEnabled?: boolean,
    public roamingOutgoingEnabled?: boolean,
    public iddEnabled?: boolean,
    public iddOptions?: string,
    public callForwardEnabled?: boolean,
    public callWaitingEnabled?: boolean,
    public clipEnabled?: boolean,
    public callBarringEnabled?: boolean,
    public mvrsEnabled?: boolean,
    public specialCallServices?: string,
    public callRoutingSupported?: boolean,
    public prbtSupported?: boolean,
    public hrbtSupported?: boolean,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string,
    public cfsServices?: ICfsServiceSpec
  ) {
    this.roamingIncomingEnabled = this.roamingIncomingEnabled || false;
    this.roamingOutgoingEnabled = this.roamingOutgoingEnabled || false;
    this.iddEnabled = this.iddEnabled || false;
    this.callForwardEnabled = this.callForwardEnabled || false;
    this.callWaitingEnabled = this.callWaitingEnabled || false;
    this.clipEnabled = this.clipEnabled || false;
    this.callBarringEnabled = this.callBarringEnabled || false;
    this.mvrsEnabled = this.mvrsEnabled || false;
    this.callRoutingSupported = this.callRoutingSupported || false;
    this.prbtSupported = this.prbtSupported || false;
    this.hrbtSupported = this.hrbtSupported || false;
  }
}
