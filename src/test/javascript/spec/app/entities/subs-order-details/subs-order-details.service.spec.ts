import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { SubsOrderDetailsService } from 'app/entities/subs-order-details/subs-order-details.service';
import { ISubsOrderDetails, SubsOrderDetails } from 'app/shared/model/subs-order-details.model';
import { Language } from 'app/shared/model/enumerations/language.model';
import { ServiceType } from 'app/shared/model/enumerations/service-type.model';

describe('Service Tests', () => {
  describe('SubsOrderDetails Service', () => {
    let injector: TestBed;
    let service: SubsOrderDetailsService;
    let httpMock: HttpTestingController;
    let elemDefault: ISubsOrderDetails;
    let expectedResult: ISubsOrderDetails | ISubsOrderDetails[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(SubsOrderDetailsService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new SubsOrderDetails(
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
        false,
        currentDate,
        'AAAAAAA',
        0,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
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
        ServiceType.PREPAID,
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
            simVerifiedDate: currentDate.format(DATE_TIME_FORMAT),
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

      it('should create a SubsOrderDetails', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            startDate: currentDate.format(DATE_TIME_FORMAT),
            endDate: currentDate.format(DATE_TIME_FORMAT),
            simVerifiedDate: currentDate.format(DATE_TIME_FORMAT),
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
            simVerifiedDate: currentDate,
            mnpRequestedDate: currentDate,
            createdDate: currentDate,
            lastUpdatedDate: currentDate
          },
          returnedFromService
        );

        service.create(new SubsOrderDetails()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SubsOrderDetails', () => {
        const returnedFromService = Object.assign(
          {
            subsOrderSeqId: 1,
            subscriptionId: 'BBBBBB',
            startDate: currentDate.format(DATE_TIME_FORMAT),
            endDate: currentDate.format(DATE_TIME_FORMAT),
            orderId: 'BBBBBB',
            ssaNbr: 'BBBBBB',
            primaryMsisdn: 'BBBBBB',
            iccid: 'BBBBBB',
            imsi: 'BBBBBB',
            simVerified: true,
            simVerifiedDate: currentDate.format(DATE_TIME_FORMAT),
            billingAcctId: 'BBBBBB',
            billCycleId: 1,
            mnpRequestedDate: currentDate.format(DATE_TIME_FORMAT),
            mnpTicket: 'BBBBBB',
            mnpPortInSession: 'BBBBBB',
            mnpOriginalId: 'BBBBBB',
            mnpCustName: 'BBBBBB',
            mnpIdNbr: 'BBBBBB',
            mnpIdType: 'BBBBBB',
            hthkMsisdn: true,
            lang: 'BBBBBB',
            offerId: 'BBBBBB',
            offerName: 'BBBBBB',
            matrixxCatalogId: 'BBBBBB',
            matrixxResourceId: 'BBBBBB',
            matrixxObjectId: 'BBBBBB',
            salesChannel: 'BBBBBB',
            advancePaymentMonths: 1,
            offerPrice: 1,
            networkType: 'BBBBBB',
            servicetype: 'BBBBBB',
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
            simVerifiedDate: currentDate,
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

      it('should return a list of SubsOrderDetails', () => {
        const returnedFromService = Object.assign(
          {
            subsOrderSeqId: 1,
            subscriptionId: 'BBBBBB',
            startDate: currentDate.format(DATE_TIME_FORMAT),
            endDate: currentDate.format(DATE_TIME_FORMAT),
            orderId: 'BBBBBB',
            ssaNbr: 'BBBBBB',
            primaryMsisdn: 'BBBBBB',
            iccid: 'BBBBBB',
            imsi: 'BBBBBB',
            simVerified: true,
            simVerifiedDate: currentDate.format(DATE_TIME_FORMAT),
            billingAcctId: 'BBBBBB',
            billCycleId: 1,
            mnpRequestedDate: currentDate.format(DATE_TIME_FORMAT),
            mnpTicket: 'BBBBBB',
            mnpPortInSession: 'BBBBBB',
            mnpOriginalId: 'BBBBBB',
            mnpCustName: 'BBBBBB',
            mnpIdNbr: 'BBBBBB',
            mnpIdType: 'BBBBBB',
            hthkMsisdn: true,
            lang: 'BBBBBB',
            offerId: 'BBBBBB',
            offerName: 'BBBBBB',
            matrixxCatalogId: 'BBBBBB',
            matrixxResourceId: 'BBBBBB',
            matrixxObjectId: 'BBBBBB',
            salesChannel: 'BBBBBB',
            advancePaymentMonths: 1,
            offerPrice: 1,
            networkType: 'BBBBBB',
            servicetype: 'BBBBBB',
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
            simVerifiedDate: currentDate,
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

      it('should delete a SubsOrderDetails', () => {
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
