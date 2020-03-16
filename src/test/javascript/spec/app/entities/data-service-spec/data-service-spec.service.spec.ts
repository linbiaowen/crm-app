import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { DataServiceSpecService } from 'app/entities/data-service-spec/data-service-spec.service';
import { IDataServiceSpec, DataServiceSpec } from 'app/shared/model/data-service-spec.model';

describe('Service Tests', () => {
  describe('DataServiceSpec Service', () => {
    let injector: TestBed;
    let service: DataServiceSpecService;
    let httpMock: HttpTestingController;
    let elemDefault: IDataServiceSpec;
    let expectedResult: IDataServiceSpec | IDataServiceSpec[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(DataServiceSpecService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new DataServiceSpec(
        'ID',
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

      it('should create a DataServiceSpec', () => {
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

        service.create(new DataServiceSpec()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a DataServiceSpec', () => {
        const returnedFromService = Object.assign(
          {
            dataSpecId: 1,
            serviceSpecId: 'BBBBBB',
            maxEntitlement: 'BBBBBB',
            maxAccessSpeed: 'BBBBBB',
            throttledSpeed: 'BBBBBB',
            upstreamSpeed: 'BBBBBB',
            downstreamSpeed: 'BBBBBB',
            socialSites: 'BBBBBB',
            entertainmentPackOptions: 'BBBBBB',
            roamingDataSpeed: 'BBBBBB',
            roamingDataVolume: 'BBBBBB',
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

      it('should return a list of DataServiceSpec', () => {
        const returnedFromService = Object.assign(
          {
            dataSpecId: 1,
            serviceSpecId: 'BBBBBB',
            maxEntitlement: 'BBBBBB',
            maxAccessSpeed: 'BBBBBB',
            throttledSpeed: 'BBBBBB',
            upstreamSpeed: 'BBBBBB',
            downstreamSpeed: 'BBBBBB',
            socialSites: 'BBBBBB',
            entertainmentPackOptions: 'BBBBBB',
            roamingDataSpeed: 'BBBBBB',
            roamingDataVolume: 'BBBBBB',
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

      it('should delete a DataServiceSpec', () => {
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
