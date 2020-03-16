import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { CustCommOptoutMasterService } from 'app/entities/cust-comm-optout-master/cust-comm-optout-master.service';
import { ICustCommOptoutMaster, CustCommOptoutMaster } from 'app/shared/model/cust-comm-optout-master.model';

describe('Service Tests', () => {
  describe('CustCommOptoutMaster Service', () => {
    let injector: TestBed;
    let service: CustCommOptoutMasterService;
    let httpMock: HttpTestingController;
    let elemDefault: ICustCommOptoutMaster;
    let expectedResult: ICustCommOptoutMaster | ICustCommOptoutMaster[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CustCommOptoutMasterService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new CustCommOptoutMaster(
        'ID',
        'AAAAAAA',
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
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            optoutStartDate: currentDate.format(DATE_TIME_FORMAT),
            optoutEndDate: currentDate.format(DATE_TIME_FORMAT),
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

      it('should create a CustCommOptoutMaster', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            optoutStartDate: currentDate.format(DATE_TIME_FORMAT),
            optoutEndDate: currentDate.format(DATE_TIME_FORMAT),
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastUpdatedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            optoutStartDate: currentDate,
            optoutEndDate: currentDate,
            createdDate: currentDate,
            lastUpdatedDate: currentDate
          },
          returnedFromService
        );

        service.create(new CustCommOptoutMaster()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CustCommOptoutMaster', () => {
        const returnedFromService = Object.assign(
          {
            optoutId: 'BBBBBB',
            custAcctId: 'BBBBBB',
            subscriptionId: 'BBBBBB',
            primaryMobNbr: 'BBBBBB',
            optoutTypeId: 'BBBBBB',
            optoutMediaId: 'BBBBBB',
            optoutStatus: 'BBBBBB',
            optoutStartDate: currentDate.format(DATE_TIME_FORMAT),
            optoutEndDate: currentDate.format(DATE_TIME_FORMAT),
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
            optoutStartDate: currentDate,
            optoutEndDate: currentDate,
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

      it('should return a list of CustCommOptoutMaster', () => {
        const returnedFromService = Object.assign(
          {
            optoutId: 'BBBBBB',
            custAcctId: 'BBBBBB',
            subscriptionId: 'BBBBBB',
            primaryMobNbr: 'BBBBBB',
            optoutTypeId: 'BBBBBB',
            optoutMediaId: 'BBBBBB',
            optoutStatus: 'BBBBBB',
            optoutStartDate: currentDate.format(DATE_TIME_FORMAT),
            optoutEndDate: currentDate.format(DATE_TIME_FORMAT),
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
            optoutStartDate: currentDate,
            optoutEndDate: currentDate,
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

      it('should delete a CustCommOptoutMaster', () => {
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
