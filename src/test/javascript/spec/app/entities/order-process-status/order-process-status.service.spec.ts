import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { OrderProcessStatusService } from 'app/entities/order-process-status/order-process-status.service';
import { IOrderProcessStatus, OrderProcessStatus } from 'app/shared/model/order-process-status.model';
import { OrderStatus } from 'app/shared/model/enumerations/order-status.model';
import { ProcessStatus } from 'app/shared/model/enumerations/process-status.model';

describe('Service Tests', () => {
  describe('OrderProcessStatus Service', () => {
    let injector: TestBed;
    let service: OrderProcessStatusService;
    let httpMock: HttpTestingController;
    let elemDefault: IOrderProcessStatus;
    let expectedResult: IOrderProcessStatus | IOrderProcessStatus[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(OrderProcessStatusService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new OrderProcessStatus(
        'ID',
        'AAAAAAA',
        OrderStatus.NEW,
        OrderStatus.NEW,
        currentDate,
        'AAAAAAA',
        ProcessStatus.NEW,
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
            statusUpdatedDate: currentDate.format(DATE_TIME_FORMAT),
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

      it('should create a OrderProcessStatus', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            statusUpdatedDate: currentDate.format(DATE_TIME_FORMAT),
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastUpdatedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            statusUpdatedDate: currentDate,
            createdDate: currentDate,
            lastUpdatedDate: currentDate
          },
          returnedFromService
        );

        service.create(new OrderProcessStatus()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a OrderProcessStatus', () => {
        const returnedFromService = Object.assign(
          {
            orderId: 'BBBBBB',
            entryOrderStatus: 'BBBBBB',
            exitOrderStatus: 'BBBBBB',
            statusUpdatedDate: currentDate.format(DATE_TIME_FORMAT),
            processName: 'BBBBBB',
            status: 'BBBBBB',
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
            statusUpdatedDate: currentDate,
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

      it('should return a list of OrderProcessStatus', () => {
        const returnedFromService = Object.assign(
          {
            orderId: 'BBBBBB',
            entryOrderStatus: 'BBBBBB',
            exitOrderStatus: 'BBBBBB',
            statusUpdatedDate: currentDate.format(DATE_TIME_FORMAT),
            processName: 'BBBBBB',
            status: 'BBBBBB',
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
            statusUpdatedDate: currentDate,
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

      it('should delete a OrderProcessStatus', () => {
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
