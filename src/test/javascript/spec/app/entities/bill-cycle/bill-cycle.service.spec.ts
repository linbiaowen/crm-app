import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { BillCycleService } from 'app/entities/bill-cycle/bill-cycle.service';
import { IBillCycle, BillCycle } from 'app/shared/model/bill-cycle.model';

describe('Service Tests', () => {
  describe('BillCycle Service', () => {
    let injector: TestBed;
    let service: BillCycleService;
    let httpMock: HttpTestingController;
    let elemDefault: IBillCycle;
    let expectedResult: IBillCycle | IBillCycle[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BillCycleService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new BillCycle(
        'ID',
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        currentDate,
        currentDate,
        0,
        0,
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
            billCycleStartDate: currentDate.format(DATE_TIME_FORMAT),
            billCycleEndDate: currentDate.format(DATE_TIME_FORMAT),
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

      it('should create a BillCycle', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            billCycleStartDate: currentDate.format(DATE_TIME_FORMAT),
            billCycleEndDate: currentDate.format(DATE_TIME_FORMAT),
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastUpdatedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            billCycleStartDate: currentDate,
            billCycleEndDate: currentDate,
            createdDate: currentDate,
            lastUpdatedDate: currentDate
          },
          returnedFromService
        );

        service.create(new BillCycle()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a BillCycle', () => {
        const returnedFromService = Object.assign(
          {
            billCycleId: 1,
            billCycleDesc: 'BBBBBB',
            billCycle: 1,
            billFrequency: 'BBBBBB',
            billCycleStartDate: currentDate.format(DATE_TIME_FORMAT),
            billCycleEndDate: currentDate.format(DATE_TIME_FORMAT),
            dueDateOffset: 1,
            directDebitProcessDay: 1,
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
            billCycleStartDate: currentDate,
            billCycleEndDate: currentDate,
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

      it('should return a list of BillCycle', () => {
        const returnedFromService = Object.assign(
          {
            billCycleId: 1,
            billCycleDesc: 'BBBBBB',
            billCycle: 1,
            billFrequency: 'BBBBBB',
            billCycleStartDate: currentDate.format(DATE_TIME_FORMAT),
            billCycleEndDate: currentDate.format(DATE_TIME_FORMAT),
            dueDateOffset: 1,
            directDebitProcessDay: 1,
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
            billCycleStartDate: currentDate,
            billCycleEndDate: currentDate,
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

      it('should delete a BillCycle', () => {
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
