import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { SubscriptionProductService } from 'app/entities/subscription-product/subscription-product.service';
import { ISubscriptionProduct, SubscriptionProduct } from 'app/shared/model/subscription-product.model';

describe('Service Tests', () => {
  describe('SubscriptionProduct Service', () => {
    let injector: TestBed;
    let service: SubscriptionProductService;
    let httpMock: HttpTestingController;
    let elemDefault: ISubscriptionProduct;
    let expectedResult: ISubscriptionProduct | ISubscriptionProduct[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(SubscriptionProductService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new SubscriptionProduct(
        'ID',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        false,
        'AAAAAAA',
        false,
        currentDate,
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            activationDate: currentDate.format(DATE_TIME_FORMAT),
            endDate: currentDate.format(DATE_TIME_FORMAT),
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastUpdatedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a SubscriptionProduct', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            activationDate: currentDate.format(DATE_TIME_FORMAT),
            endDate: currentDate.format(DATE_TIME_FORMAT),
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastUpdatedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            activationDate: currentDate,
            endDate: currentDate,
            createdDate: currentDate,
            lastUpdatedDate: currentDate
          },
          returnedFromService
        );

        service.create(new SubscriptionProduct()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SubscriptionProduct', () => {
        const returnedFromService = Object.assign(
          {
            productSubsriptionSeqId: 1,
            orderId: 'BBBBBB',
            subscriptionId: 'BBBBBB',
            productId: 'BBBBBB',
            productName: 'BBBBBB',
            activationDate: currentDate.format(DATE_TIME_FORMAT),
            endDate: currentDate.format(DATE_TIME_FORMAT),
            secondMsisdn: 'BBBBBB',
            secondImsi: 'BBBBBB',
            quantity: 1,
            terminationReasonCode: 'BBBBBB',
            offerId: 'BBBBBB',
            offerName: 'BBBBBB',
            offerType: 'BBBBBB',
            matrixxCatalogId: 'BBBBBB',
            matrixxResourceId: 'BBBBBB',
            matrixxObjectId: 'BBBBBB',
            salesChannel: 'BBBBBB',
            contractId: 'BBBBBB',
            autoRenewal: true,
            autoPay: true,
            remarks: 'BBBBBB',
            vendorProvisionInd: true,
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB',
            lastUpdatedDate: currentDate.format(DATE_TIME_FORMAT),
            lastUpdatedBy: 'BBBBBB',
            tenantId: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            activationDate: currentDate,
            endDate: currentDate,
            createdDate: currentDate,
            lastUpdatedDate: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of SubscriptionProduct', () => {
        const returnedFromService = Object.assign(
          {
            productSubsriptionSeqId: 1,
            orderId: 'BBBBBB',
            subscriptionId: 'BBBBBB',
            productId: 'BBBBBB',
            productName: 'BBBBBB',
            activationDate: currentDate.format(DATE_TIME_FORMAT),
            endDate: currentDate.format(DATE_TIME_FORMAT),
            secondMsisdn: 'BBBBBB',
            secondImsi: 'BBBBBB',
            quantity: 1,
            terminationReasonCode: 'BBBBBB',
            offerId: 'BBBBBB',
            offerName: 'BBBBBB',
            offerType: 'BBBBBB',
            matrixxCatalogId: 'BBBBBB',
            matrixxResourceId: 'BBBBBB',
            matrixxObjectId: 'BBBBBB',
            salesChannel: 'BBBBBB',
            contractId: 'BBBBBB',
            autoRenewal: true,
            autoPay: true,
            remarks: 'BBBBBB',
            vendorProvisionInd: true,
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB',
            lastUpdatedDate: currentDate.format(DATE_TIME_FORMAT),
            lastUpdatedBy: 'BBBBBB',
            tenantId: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            activationDate: currentDate,
            endDate: currentDate,
            createdDate: currentDate,
            lastUpdatedDate: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a SubscriptionProduct', () => {
        service.delete('123').subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
