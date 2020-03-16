import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { OfferSpecificationService } from 'app/entities/offer-specification/offer-specification.service';
import { IOfferSpecification, OfferSpecification } from 'app/shared/model/offer-specification.model';

describe('Service Tests', () => {
  describe('OfferSpecification Service', () => {
    let injector: TestBed;
    let service: OfferSpecificationService;
    let httpMock: HttpTestingController;
    let elemDefault: IOfferSpecification;
    let expectedResult: IOfferSpecification | IOfferSpecification[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(OfferSpecificationService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new OfferSpecification(
        'ID',
        0,
        'AAAAAAA',
        currentDate,
        currentDate,
        false,
        currentDate,
        currentDate,
        false,
        false,
        false,
        false,
        false,
        'AAAAAAA',
        0,
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
            allowedActivationStartDate: currentDate.format(DATE_TIME_FORMAT),
            allowedActivationEndDate: currentDate.format(DATE_TIME_FORMAT),
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

      it('should create a OfferSpecification', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            startDate: currentDate.format(DATE_TIME_FORMAT),
            endDate: currentDate.format(DATE_TIME_FORMAT),
            allowedActivationStartDate: currentDate.format(DATE_TIME_FORMAT),
            allowedActivationEndDate: currentDate.format(DATE_TIME_FORMAT),
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastUpdatedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            startDate: currentDate,
            endDate: currentDate,
            allowedActivationStartDate: currentDate,
            allowedActivationEndDate: currentDate,
            createdDate: currentDate,
            lastUpdatedDate: currentDate
          },
          returnedFromService
        );

        service.create(new OfferSpecification()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a OfferSpecification', () => {
        const returnedFromService = Object.assign(
          {
            offerSpecId: 1,
            offerId: 'BBBBBB',
            startDate: currentDate.format(DATE_TIME_FORMAT),
            endDate: currentDate.format(DATE_TIME_FORMAT),
            limitedActivationPeriod: true,
            allowedActivationStartDate: currentDate.format(DATE_TIME_FORMAT),
            allowedActivationEndDate: currentDate.format(DATE_TIME_FORMAT),
            isGroupSharingOffer: true,
            isMnpOffer: true,
            autoRenewal: true,
            transferAllowed: true,
            infoSharingAllowed: true,
            infoSharingOptions: 'BBBBBB',
            offerPeriod: 1,
            offerPeriodTerm: 'BBBBBB',
            paymentType: 'BBBBBB',
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
            allowedActivationStartDate: currentDate,
            allowedActivationEndDate: currentDate,
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

      it('should return a list of OfferSpecification', () => {
        const returnedFromService = Object.assign(
          {
            offerSpecId: 1,
            offerId: 'BBBBBB',
            startDate: currentDate.format(DATE_TIME_FORMAT),
            endDate: currentDate.format(DATE_TIME_FORMAT),
            limitedActivationPeriod: true,
            allowedActivationStartDate: currentDate.format(DATE_TIME_FORMAT),
            allowedActivationEndDate: currentDate.format(DATE_TIME_FORMAT),
            isGroupSharingOffer: true,
            isMnpOffer: true,
            autoRenewal: true,
            transferAllowed: true,
            infoSharingAllowed: true,
            infoSharingOptions: 'BBBBBB',
            offerPeriod: 1,
            offerPeriodTerm: 'BBBBBB',
            paymentType: 'BBBBBB',
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
            allowedActivationStartDate: currentDate,
            allowedActivationEndDate: currentDate,
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

      it('should delete a OfferSpecification', () => {
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
