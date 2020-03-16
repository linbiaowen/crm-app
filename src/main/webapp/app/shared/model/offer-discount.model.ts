import { Moment } from 'moment';
import { IOffer } from 'app/shared/model/offer.model';
import { RecordStatus } from 'app/shared/model/enumerations/record-status.model';

export interface IOfferDiscount {
  id?: string;
  discountCode?: string;
  offerId?: string;
  startDate?: Moment;
  endDate?: Moment;
  discountType?: string;
  offerDiscount?: number;
  status?: RecordStatus;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  offer?: IOffer;
}

export class OfferDiscount implements IOfferDiscount {
  constructor(
    public id?: string,
    public discountCode?: string,
    public offerId?: string,
    public startDate?: Moment,
    public endDate?: Moment,
    public discountType?: string,
    public offerDiscount?: number,
    public status?: RecordStatus,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string,
    public offer?: IOffer
  ) {}
}
