import { Moment } from 'moment';
import { IOffer } from 'app/shared/model/offer.model';
import { RecordStatus } from 'app/shared/model/enumerations/record-status.model';

export interface IOfferAdvancePayment {
  id?: string;
  advancePaymentId?: number;
  offerId?: string;
  advancePaymentMonths?: number;
  offerPrice?: number;
  offerDiscount?: number;
  status?: RecordStatus;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  offer?: IOffer;
}

export class OfferAdvancePayment implements IOfferAdvancePayment {
  constructor(
    public id?: string,
    public advancePaymentId?: number,
    public offerId?: string,
    public advancePaymentMonths?: number,
    public offerPrice?: number,
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
