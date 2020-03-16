import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { SubsItemDeliveryService } from 'app/entities/subs-item-delivery/subs-item-delivery.service';
import { ISubsItemDelivery, SubsItemDelivery } from 'app/shared/model/subs-item-delivery.model';

describe('Service Tests', () => {
  describe('SubsItemDelivery Service', () => {
    let injector: TestBed;
    let service: SubsItemDeliveryService;
    let httpMock: HttpTestingController;
    let elemDefault: ISubsItemDelivery;
    let expectedResult: ISubsItemDelivery | ISubsItemDelivery[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(SubsItemDeliveryService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new SubsItemDelivery(
        'ID',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        currentDate,
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
            fileGenerationDate: currentDate.format(DATE_TIME_FORMAT),
            fileReceivedDate: currentDate.format(DATE_TIME_FORMAT),
            deliveryDate: currentDate.format(DATE_TIME_FORMAT),
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

      it('should create a SubsItemDelivery', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            fileGenerationDate: currentDate.format(DATE_TIME_FORMAT),
            fileReceivedDate: currentDate.format(DATE_TIME_FORMAT),
            deliveryDate: currentDate.format(DATE_TIME_FORMAT),
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastUpdatedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fileGenerationDate: currentDate,
            fileReceivedDate: currentDate,
            deliveryDate: currentDate,
            createdDate: currentDate,
            lastUpdatedDate: currentDate
          },
          returnedFromService
        );

        service.create(new SubsItemDelivery()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SubsItemDelivery', () => {
        const returnedFromService = Object.assign(
          {
            deliveryId: 1,
            orderId: 'BBBBBB',
            subscriptionId: 'BBBBBB',
            subscriptionItemId: 'BBBBBB',
            deliveryStatus: 'BBBBBB',
            deliveryMethodId: 'BBBBBB',
            deliveryRefCode: 'BBBBBB',
            fileGenerationDate: currentDate.format(DATE_TIME_FORMAT),
            fileReceivedDate: currentDate.format(DATE_TIME_FORMAT),
            deliveryDate: currentDate.format(DATE_TIME_FORMAT),
            remarks: 'BBBBBB',
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
            fileGenerationDate: currentDate,
            fileReceivedDate: currentDate,
            deliveryDate: currentDate,
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

      it('should return a list of SubsItemDelivery', () => {
        const returnedFromService = Object.assign(
          {
            deliveryId: 1,
            orderId: 'BBBBBB',
            subscriptionId: 'BBBBBB',
            subscriptionItemId: 'BBBBBB',
            deliveryStatus: 'BBBBBB',
            deliveryMethodId: 'BBBBBB',
            deliveryRefCode: 'BBBBBB',
            fileGenerationDate: currentDate.format(DATE_TIME_FORMAT),
            fileReceivedDate: currentDate.format(DATE_TIME_FORMAT),
            deliveryDate: currentDate.format(DATE_TIME_FORMAT),
            remarks: 'BBBBBB',
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
            fileGenerationDate: currentDate,
            fileReceivedDate: currentDate,
            deliveryDate: currentDate,
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

      it('should delete a SubsItemDelivery', () => {
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
