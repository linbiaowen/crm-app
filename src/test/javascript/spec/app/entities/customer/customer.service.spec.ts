import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { CustomerService } from 'app/entities/customer/customer.service';
import { ICustomer, Customer } from 'app/shared/model/customer.model';
import { AcctStatus } from 'app/shared/model/enumerations/acct-status.model';
import { Language } from 'app/shared/model/enumerations/language.model';
import { CustomerSegment } from 'app/shared/model/enumerations/customer-segment.model';

describe('Service Tests', () => {
  describe('Customer Service', () => {
    let injector: TestBed;
    let service: CustomerService;
    let httpMock: HttpTestingController;
    let elemDefault: ICustomer;
    let expectedResult: ICustomer | ICustomer[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CustomerService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Customer(
        'ID',
        'AAAAAAA',
        'AAAAAAA',
        AcctStatus.PREACTIVE,
        currentDate,
        currentDate,
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
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        Language.CHINESE,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        CustomerSegment.MASS,
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
            acctStartDate: currentDate.format(DATE_TIME_FORMAT),
            acctEndDate: currentDate.format(DATE_TIME_FORMAT),
            birthDate: currentDate.format(DATE_FORMAT),
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

      it('should create a Customer', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            acctStartDate: currentDate.format(DATE_TIME_FORMAT),
            acctEndDate: currentDate.format(DATE_TIME_FORMAT),
            birthDate: currentDate.format(DATE_FORMAT),
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastUpdatedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            acctStartDate: currentDate,
            acctEndDate: currentDate,
            birthDate: currentDate,
            createdDate: currentDate,
            lastUpdatedDate: currentDate
          },
          returnedFromService
        );

        service.create(new Customer()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Customer', () => {
        const returnedFromService = Object.assign(
          {
            custAcctId: 'BBBBBB',
            parentCustAcctId: 'BBBBBB',
            acctStatus: 'BBBBBB',
            acctStartDate: currentDate.format(DATE_TIME_FORMAT),
            acctEndDate: currentDate.format(DATE_TIME_FORMAT),
            cabsAcctId: 'BBBBBB',
            title: 'BBBBBB',
            givenName: 'BBBBBB',
            familyName: 'BBBBBB',
            givenNameChi: 'BBBBBB',
            familyNameChi: 'BBBBBB',
            aliasName: 'BBBBBB',
            gender: 'BBBBBB',
            birthDate: currentDate.format(DATE_FORMAT),
            idType: 'BBBBBB',
            idNumber: 'BBBBBB',
            companyNameEng: 'BBBBBB',
            companyNameChi: 'BBBBBB',
            unlimitedCompany: true,
            lang: 'BBBBBB',
            selfCareUserId: 'BBBBBB',
            selfCarePassword: 'BBBBBB',
            ivrPin: 'BBBBBB',
            maritialStatus: 'BBBBBB',
            customerSegment: 'BBBBBB',
            customerClass: 'BBBBBB',
            billingAcctId: 'BBBBBB',
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
            acctStartDate: currentDate,
            acctEndDate: currentDate,
            birthDate: currentDate,
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

      it('should return a list of Customer', () => {
        const returnedFromService = Object.assign(
          {
            custAcctId: 'BBBBBB',
            parentCustAcctId: 'BBBBBB',
            acctStatus: 'BBBBBB',
            acctStartDate: currentDate.format(DATE_TIME_FORMAT),
            acctEndDate: currentDate.format(DATE_TIME_FORMAT),
            cabsAcctId: 'BBBBBB',
            title: 'BBBBBB',
            givenName: 'BBBBBB',
            familyName: 'BBBBBB',
            givenNameChi: 'BBBBBB',
            familyNameChi: 'BBBBBB',
            aliasName: 'BBBBBB',
            gender: 'BBBBBB',
            birthDate: currentDate.format(DATE_FORMAT),
            idType: 'BBBBBB',
            idNumber: 'BBBBBB',
            companyNameEng: 'BBBBBB',
            companyNameChi: 'BBBBBB',
            unlimitedCompany: true,
            lang: 'BBBBBB',
            selfCareUserId: 'BBBBBB',
            selfCarePassword: 'BBBBBB',
            ivrPin: 'BBBBBB',
            maritialStatus: 'BBBBBB',
            customerSegment: 'BBBBBB',
            customerClass: 'BBBBBB',
            billingAcctId: 'BBBBBB',
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
            acctStartDate: currentDate,
            acctEndDate: currentDate,
            birthDate: currentDate,
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

      it('should delete a Customer', () => {
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
