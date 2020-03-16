import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { SubscriptionDetailsService } from 'app/entities/subscription-details/subscription-details.service';
import { ISubscriptionDetails, SubscriptionDetails } from 'app/shared/model/subscription-details.model';
import { Language } from 'app/shared/model/enumerations/language.model';

describe('Service Tests', () => {
  describe('SubscriptionDetails Service', () => {
    let injector: TestBed;
    let service: SubscriptionDetailsService;
    let httpMock: HttpTestingController;
    let elemDefault: ISubscriptionDetails;
    let expectedResult: ISubscriptionDetails | ISubscriptionDetails[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(SubscriptionDetailsService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new SubscriptionDetails(
        'ID',
        0,
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        Language.CHINESE,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
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
            startDate: currentDate.format(DATE_TIME_FORMAT),
            endDate: currentDate.format(DATE_TIME_FORMAT),
            mnpRequestedDate: currentDate.format(DATE_TIME_FORMAT),
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

      it('should create a SubscriptionDetails', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            startDate: currentDate.format(DATE_TIME_FORMAT),
            endDate: currentDate.format(DATE_TIME_FORMAT),
            mnpRequestedDate: currentDate.format(DATE_TIME_FORMAT),
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastUpdatedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            startDate: currentDate,
            endDate: currentDate,
            mnpRequestedDate: currentDate,
            createdDate: currentDate,
            lastUpdatedDate: currentDate
          },
          returnedFromService
        );

        service.create(new SubscriptionDetails()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SubscriptionDetails', () => {
        const returnedFromService = Object.assign(
          {
            subsDetailId: 1,
            subscriptionId: 'BBBBBB',
            startDate: currentDate.format(DATE_TIME_FORMAT),
            endDate: currentDate.format(DATE_TIME_FORMAT),
            orderId: 'BBBBBB',
            ssaNbr: 'BBBBBB',
            primaryMsisdn: 'BBBBBB',
            iccid: 'BBBBBB',
            imsi: 'BBBBBB',
            mnpRequestedDate: currentDate.format(DATE_TIME_FORMAT),
            lang: 'BBBBBB',
            baseOfferId: 'BBBBBB',
            baseOfferName: 'BBBBBB',
            matrixxCatalogId: 'BBBBBB',
            matrixxResourceId: 'BBBBBB',
            matrixxObjectId: 'BBBBBB',
            salesChannel: 'BBBBBB',
            advancePaymentMonths: 1,
            offerPrice: 1,
            networkType: 'BBBBBB',
            serviceType: 'BBBBBB',
            offerPlanCode: 'BBBBBB',
            serviceInPerson: 'BBBBBB',
            fcmToken: 'BBBBBB',
            remarks: 'BBBBBB',
            cdVersion: 'BBBBBB',
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
            startDate: currentDate,
            endDate: currentDate,
            mnpRequestedDate: currentDate,
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

      it('should return a list of SubscriptionDetails', () => {
        const returnedFromService = Object.assign(
          {
            subsDetailId: 1,
            subscriptionId: 'BBBBBB',
            startDate: currentDate.format(DATE_TIME_FORMAT),
            endDate: currentDate.format(DATE_TIME_FORMAT),
            orderId: 'BBBBBB',
            ssaNbr: 'BBBBBB',
            primaryMsisdn: 'BBBBBB',
            iccid: 'BBBBBB',
            imsi: 'BBBBBB',
            mnpRequestedDate: currentDate.format(DATE_TIME_FORMAT),
            lang: 'BBBBBB',
            baseOfferId: 'BBBBBB',
            baseOfferName: 'BBBBBB',
            matrixxCatalogId: 'BBBBBB',
            matrixxResourceId: 'BBBBBB',
            matrixxObjectId: 'BBBBBB',
            salesChannel: 'BBBBBB',
            advancePaymentMonths: 1,
            offerPrice: 1,
            networkType: 'BBBBBB',
            serviceType: 'BBBBBB',
            offerPlanCode: 'BBBBBB',
            serviceInPerson: 'BBBBBB',
            fcmToken: 'BBBBBB',
            remarks: 'BBBBBB',
            cdVersion: 'BBBBBB',
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
            startDate: currentDate,
            endDate: currentDate,
            mnpRequestedDate: currentDate,
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

      it('should delete a SubscriptionDetails', () => {
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
