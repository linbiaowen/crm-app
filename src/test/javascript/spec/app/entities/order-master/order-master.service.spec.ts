import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { OrderMasterService } from 'app/entities/order-master/order-master.service';
import { IOrderMaster, OrderMaster } from 'app/shared/model/order-master.model';
import { OrderType } from 'app/shared/model/enumerations/order-type.model';
import { SubOrderType } from 'app/shared/model/enumerations/sub-order-type.model';
import { OrderNature } from 'app/shared/model/enumerations/order-nature.model';
import { OrderStatus } from 'app/shared/model/enumerations/order-status.model';

describe('Service Tests', () => {
  describe('OrderMaster Service', () => {
    let injector: TestBed;
    let service: OrderMasterService;
    let httpMock: HttpTestingController;
    let elemDefault: IOrderMaster;
    let expectedResult: IOrderMaster | IOrderMaster[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(OrderMasterService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new OrderMaster(
        'ID',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        OrderType.PURCHASE_OFFER,
        SubOrderType.OFFER,
        OrderNature.NORMAL,
        false,
        OrderStatus.NEW,
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

      it('should create a OrderMaster', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastUpdatedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            lastUpdatedDate: currentDate
          },
          returnedFromService
        );

        service.create(new OrderMaster()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a OrderMaster', () => {
        const returnedFromService = Object.assign(
          {
            orderId: 'BBBBBB',
            custAcctId: 'BBBBBB',
            subscriptionId: 'BBBBBB',
            orderType: 'BBBBBB',
            subOrderType: 'BBBBBB',
            orderNature: 'BBBBBB',
            isChangePlan: true,
            orderStatus: 'BBBBBB',
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

      it('should return a list of OrderMaster', () => {
        const returnedFromService = Object.assign(
          {
            orderId: 'BBBBBB',
            custAcctId: 'BBBBBB',
            subscriptionId: 'BBBBBB',
            orderType: 'BBBBBB',
            subOrderType: 'BBBBBB',
            orderNature: 'BBBBBB',
            isChangePlan: true,
            orderStatus: 'BBBBBB',
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

      it('should delete a OrderMaster', () => {
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
