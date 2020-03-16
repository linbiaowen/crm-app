import { Moment } from 'moment';
import { IOffer } from 'app/shared/model/offer.model';

export interface IOfferSpecification {
  id?: string;
  offerSpecId?: number;
  offerId?: string;
  startDate?: Moment;
  endDate?: Moment;
  limitedActivationPeriod?: boolean;
  allowedActivationStartDate?: Moment;
  allowedActivationEndDate?: Moment;
  isGroupSharingOffer?: boolean;
  isMnpOffer?: boolean;
  autoRenewal?: boolean;
  transferAllowed?: boolean;
  infoSharingAllowed?: boolean;
  infoSharingOptions?: string;
  offerPeriod?: number;
  offerPeriodTerm?: string;
  paymentType?: string;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  offer?: IOffer;
}

export class OfferSpecification implements IOfferSpecification {
  constructor(
    public id?: string,
    public offerSpecId?: number,
    public offerId?: string,
    public startDate?: Moment,
    public endDate?: Moment,
    public limitedActivationPeriod?: boolean,
    public allowedActivationStartDate?: Moment,
    public allowedActivationEndDate?: Moment,
    public isGroupSharingOffer?: boolean,
    public isMnpOffer?: boolean,
    public autoRenewal?: boolean,
    public transferAllowed?: boolean,
    public infoSharingAllowed?: boolean,
    public infoSharingOptions?: string,
    public offerPeriod?: number,
    public offerPeriodTerm?: string,
    public paymentType?: string,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string,
    public offer?: IOffer
  ) {
    this.limitedActivationPeriod = this.limitedActivationPeriod || false;
    this.isGroupSharingOffer = this.isGroupSharingOffer || false;
    this.isMnpOffer = this.isMnpOffer || false;
    this.autoRenewal = this.autoRenewal || false;
    this.transferAllowed = this.transferAllowed || false;
    this.infoSharingAllowed = this.infoSharingAllowed || false;
  }
}
