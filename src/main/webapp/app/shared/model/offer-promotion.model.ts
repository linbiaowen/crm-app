import { Moment } from 'moment';
import { IOffer } from 'app/shared/model/offer.model';
import { RecordStatus } from 'app/shared/model/enumerations/record-status.model';

export interface IOfferPromotion {
  id?: string;
  promoCode?: string;
  offerId?: string;
  startDate?: Moment;
  endDate?: Moment;
  promoType?: string;
  offerPrice?: number;
  offerDiscount?: number;
  freeDataOfferId?: string;
  status?: RecordStatus;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  offer?: IOffer;
}

export class OfferPromotion implements IOfferPromotion {
  constructor(
    public id?: string,
    public promoCode?: string,
    public offerId?: string,
    public startDate?: Moment,
    public endDate?: Moment,
    public promoType?: string,
    public offerPrice?: number,
    public offerDiscount?: number,
    public freeDataOfferId?: string,
    public status?: RecordStatus,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string,
    public offer?: IOffer
  ) {}
}
