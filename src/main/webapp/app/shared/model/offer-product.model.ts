import { Moment } from 'moment';
import { IOffer } from 'app/shared/model/offer.model';

export interface IOfferProduct {
  id?: string;
  recSeqId?: number;
  productId?: string;
  offerId?: string;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  offer?: IOffer;
}

export class OfferProduct implements IOfferProduct {
  constructor(
    public id?: string,
    public recSeqId?: number,
    public productId?: string,
    public offerId?: string,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string,
    public offer?: IOffer
  ) {}
}
