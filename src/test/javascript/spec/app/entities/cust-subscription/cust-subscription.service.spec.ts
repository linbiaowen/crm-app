import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { CustSubscriptionService } from 'app/entities/cust-subscription/cust-subscription.service';
import { ICustSubscription, CustSubscription } from 'app/shared/model/cust-subscription.model';
import { SubscriptionStatus } from 'app/shared/model/enumerations/subscription-status.model';

describe('Service Tests', () => {
  describe('CustSubscription Service', () => {
    let injector: TestBed;
    let service: CustSubscriptionService;
    let httpMock: HttpTestingController;
    let elemDefault: ICustSubscription;
    let expectedResult: ICustSubscription | ICustSubscription[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CustSubscriptionService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new CustSubscription(
        'ID',
        'AAAAAAA',
        currentDate,
        currentDate,
        currentDate,
        currentDate,
        SubscriptionStatus.PREACTIVE,
        false,
        'AAAAAAA',
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
            subsEndDate: currentDate.format(DATE_TIME_FORMAT),
            subsPurchaseDate: currentDate.format(DATE_TIME_FORMAT),
            origServiceStartDate: currentDate.format(DATE_TIME_FORMAT),
            lastStatusUpdatedDate: currentDate.format(DATE_TIME_FORMAT),
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

      it('should create a CustSubscription', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            activationDate: currentDate.format(DATE_TIME_FORMAT),
            subsEndDate: currentDate.format(DATE_TIME_FORMAT),
            subsPurchaseDate: currentDate.format(DATE_TIME_FORMAT),
            origServiceStartDate: currentDate.format(DATE_TIME_FORMAT),
            lastStatusUpdatedDate: currentDate.format(DATE_TIME_FORMAT),
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastUpdatedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            activationDate: currentDate,
            subsEndDate: currentDate,
            subsPurchaseDate: currentDate,
            origServiceStartDate: currentDate,
            lastStatusUpdatedDate: currentDate,
            createdDate: currentDate,
            lastUpdatedDate: currentDate
          },
          returnedFromService
        );

        service.create(new CustSubscription()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CustSubscription', () => {
        const returnedFromService = Object.assign(
          {
            subscriptionId: 'BBBBBB',
            activationDate: currentDate.format(DATE_TIME_FORMAT),
            subsEndDate: currentDate.format(DATE_TIME_FORMAT),
            subsPurchaseDate: currentDate.format(DATE_TIME_FORMAT),
            origServiceStartDate: currentDate.format(DATE_TIME_FORMAT),
            status: 'BBBBBB',
            primarySubsInd: true,
            subsLastStatusCode: 'BBBBBB',
            lastStatusUpdatedDate: currentDate.format(DATE_TIME_FORMAT),
            custAcctId: 'BBBBBB',
            billingAcctId: 'BBBBBB',
            billCycleId: 1,
            orderId: 'BBBBBB',
            matrixxObjectId: 'BBBBBB',
            subscriberName: 'BBBBBB',
            subsDeptName: 'BBBBBB',
            selfCarePassword: 'BBBBBB',
            subsCategory: 'BBBBBB',
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
            subsEndDate: currentDate,
            subsPurchaseDate: currentDate,
            origServiceStartDate: currentDate,
            lastStatusUpdatedDate: currentDate,
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

      it('should return a list of CustSubscription', () => {
        const returnedFromService = Object.assign(
          {
            subscriptionId: 'BBBBBB',
            activationDate: currentDate.format(DATE_TIME_FORMAT),
            subsEndDate: currentDate.format(DATE_TIME_FORMAT),
            subsPurchaseDate: currentDate.format(DATE_TIME_FORMAT),
            origServiceStartDate: currentDate.format(DATE_TIME_FORMAT),
            status: 'BBBBBB',
            primarySubsInd: true,
            subsLastStatusCode: 'BBBBBB',
            lastStatusUpdatedDate: currentDate.format(DATE_TIME_FORMAT),
            custAcctId: 'BBBBBB',
            billingAcctId: 'BBBBBB',
            billCycleId: 1,
            orderId: 'BBBBBB',
            matrixxObjectId: 'BBBBBB',
            subscriberName: 'BBBBBB',
            subsDeptName: 'BBBBBB',
            selfCarePassword: 'BBBBBB',
            subsCategory: 'BBBBBB',
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
            subsEndDate: currentDate,
            subsPurchaseDate: currentDate,
            origServiceStartDate: currentDate,
            lastStatusUpdatedDate: currentDate,
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

      it('should delete a CustSubscription', () => {
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
