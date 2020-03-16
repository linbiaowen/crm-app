import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ProductDataService } from 'app/entities/product-data/product-data.service';
import { IProductData, ProductData } from 'app/shared/model/product-data.model';

describe('Service Tests', () => {
  describe('ProductData Service', () => {
    let injector: TestBed;
    let service: ProductDataService;
    let httpMock: HttpTestingController;
    let elemDefault: IProductData;
    let expectedResult: IProductData | IProductData[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ProductDataService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ProductData(
        'ID',
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        0,
        0,
        0,
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

      it('should create a ProductData', () => {
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

        service.create(new ProductData()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ProductData', () => {
        const returnedFromService = Object.assign(
          {
            dataId: 'BBBBBB',
            productSpecId: 1,
            unit: 'BBBBBB',
            volume: 1,
            dataSlab: 'BBBBBB',
            dataSpeedCategory: 'BBBBBB',
            specicalPackType: 'BBBBBB',
            dataServiceType: 'BBBBBB',
            roamingRegions: 'BBBBBB',
            roamingCountries: 'BBBBBB',
            roamingDayPassType: 'BBBBBB',
            roamingPackValidPeriodType: 'BBBBBB',
            roamingPackPeriod: 1,
            roamingPackTerm: 'BBBBBB',
            minTransferQuota: 1,
            maxTransferQuota: 1,
            minRetentionQuota: 1,
            minTpTransferQuota: 1,
            maxTpTransferQuota: 1,
            minTpRetentionQuota: 1,
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

      it('should return a list of ProductData', () => {
        const returnedFromService = Object.assign(
          {
            dataId: 'BBBBBB',
            productSpecId: 1,
            unit: 'BBBBBB',
            volume: 1,
            dataSlab: 'BBBBBB',
            dataSpeedCategory: 'BBBBBB',
            specicalPackType: 'BBBBBB',
            dataServiceType: 'BBBBBB',
            roamingRegions: 'BBBBBB',
            roamingCountries: 'BBBBBB',
            roamingDayPassType: 'BBBBBB',
            roamingPackValidPeriodType: 'BBBBBB',
            roamingPackPeriod: 1,
            roamingPackTerm: 'BBBBBB',
            minTransferQuota: 1,
            maxTransferQuota: 1,
            minRetentionQuota: 1,
            minTpTransferQuota: 1,
            maxTpTransferQuota: 1,
            minTpRetentionQuota: 1,
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

      it('should delete a ProductData', () => {
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
