import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { CustAddressService } from 'app/entities/cust-address/cust-address.service';
import { ICustAddress, CustAddress } from 'app/shared/model/cust-address.model';
import { AccountType } from 'app/shared/model/enumerations/account-type.model';
import { AddressType } from 'app/shared/model/enumerations/address-type.model';
import { Language } from 'app/shared/model/enumerations/language.model';

describe('Service Tests', () => {
  describe('CustAddress Service', () => {
    let injector: TestBed;
    let service: CustAddressService;
    let httpMock: HttpTestingController;
    let elemDefault: ICustAddress;
    let expectedResult: ICustAddress | ICustAddress[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CustAddressService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new CustAddress(
        'ID',
        0,
        'AAAAAAA',
        AccountType.CUSTOMER,
        AddressType.BILLING,
        Language.CHINESE,
        false,
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

      it('should create a CustAddress', () => {
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

        service.create(new CustAddress()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CustAddress', () => {
        const returnedFromService = Object.assign(
          {
            addressId: 1,
            accountId: 'BBBBBB',
            accountType: 'BBBBBB',
            addressType: 'BBBBBB',
            addressLang: 'BBBBBB',
            formattedAddress: true,
            room: 'BBBBBB',
            floor: 'BBBBBB',
            block: 'BBBBBB',
            building: 'BBBBBB',
            streetEstate: 'BBBBBB',
            district: 'BBBBBB',
            region: 'BBBBBB',
            address1: 'BBBBBB',
            address2: 'BBBBBB',
            address3: 'BBBBBB',
            address4: 'BBBBBB',
            address5: 'BBBBBB',
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

      it('should return a list of CustAddress', () => {
        const returnedFromService = Object.assign(
          {
            addressId: 1,
            accountId: 'BBBBBB',
            accountType: 'BBBBBB',
            addressType: 'BBBBBB',
            addressLang: 'BBBBBB',
            formattedAddress: true,
            room: 'BBBBBB',
            floor: 'BBBBBB',
            block: 'BBBBBB',
            building: 'BBBBBB',
            streetEstate: 'BBBBBB',
            district: 'BBBBBB',
            region: 'BBBBBB',
            address1: 'BBBBBB',
            address2: 'BBBBBB',
            address3: 'BBBBBB',
            address4: 'BBBBBB',
            address5: 'BBBBBB',
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

      it('should delete a CustAddress', () => {
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
