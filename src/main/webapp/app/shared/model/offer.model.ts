import { Moment } from 'moment';
import { IOfferSpecification } from 'app/shared/model/offer-specification.model';
import { IOfferProduct } from 'app/shared/model/offer-product.model';
import { IProductSpecification } from 'app/shared/model/product-specification.model';
import { IOfferAdvancePayment } from 'app/shared/model/offer-advance-payment.model';
import { IOfferPromotion } from 'app/shared/model/offer-promotion.model';
import { IOfferDiscount } from 'app/shared/model/offer-discount.model';
import { OfferType } from 'app/shared/model/enumerations/offer-type.model';

export interface IOffer {
  id?: string;
  offerId?: string;
  offerExternalId?: string;
  offerName?: string;
  offerNameChi?: string;
  offerType?: OfferType;
  offerPrice?: number;
  customerSegments?: string;
  customerClasses?: string;
  salesChannels?: string;
  startDate?: Moment;
  endDate?: Moment;
  childOfferIds?: string;
  productSpecIds?: string;
  advancePaymentIds?: string;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
  offerSpec?: IOfferSpecification;
  offerProducts?: IOfferProduct[];
  productSpecifications?: IProductSpecification[];
  offerAdvancePayments?: IOfferAdvancePayment[];
  offerPromotions?: IOfferPromotion[];
  offerDiscounts?: IOfferDiscount[];
  parentOffers?: IOffer[];
  childOffers?: IOffer[];
}

export class Offer implements IOffer {
  constructor(
    public id?: string,
    public offerId?: string,
    public offerExternalId?: string,
    public offerName?: string,
    public offerNameChi?: string,
    public offerType?: OfferType,
    public offerPrice?: number,
    public customerSegments?: string,
    public customerClasses?: string,
    public salesChannels?: string,
    public startDate?: Moment,
    public endDate?: Moment,
    public childOfferIds?: string,
    public productSpecIds?: string,
    public advancePaymentIds?: string,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string,
    public offerSpec?: IOfferSpecification,
    public offerProducts?: IOfferProduct[],
    public productSpecifications?: IProductSpecification[],
    public offerAdvancePayments?: IOfferAdvancePayment[],
    public offerPromotions?: IOfferPromotion[],
    public offerDiscounts?: IOfferDiscount[],
    public parentOffers?: IOffer[],
    public childOffers?: IOffer[]
  ) {}
}
