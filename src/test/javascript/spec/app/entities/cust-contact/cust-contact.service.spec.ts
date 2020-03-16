import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { CustContactService } from 'app/entities/cust-contact/cust-contact.service';
import { ICustContact, CustContact } from 'app/shared/model/cust-contact.model';
import { AccountType } from 'app/shared/model/enumerations/account-type.model';
import { ContactType } from 'app/shared/model/enumerations/contact-type.model';
import { RecordStatus } from 'app/shared/model/enumerations/record-status.model';

describe('Service Tests', () => {
  describe('CustContact Service', () => {
    let injector: TestBed;
    let service: CustContactService;
    let httpMock: HttpTestingController;
    let elemDefault: ICustContact;
    let expectedResult: ICustContact | ICustContact[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CustContactService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new CustContact(
        'ID',
        0,
        'AAAAAAA',
        AccountType.CUSTOMER,
        ContactType.HOME,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        RecordStatus.INACTIVE,
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

      it('should create a CustContact', () => {
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

        service.create(new CustContact()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CustContact', () => {
        const returnedFromService = Object.assign(
          {
            contactId: 1,
            accountId: 'BBBBBB',
            accountType: 'BBBBBB',
            contactType: 'BBBBBB',
            contactPerson: 'BBBBBB',
            contactPhone: 'BBBBBB',
            contactEmail: 'BBBBBB',
            status: 'BBBBBB',
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

      it('should return a list of CustContact', () => {
        const returnedFromService = Object.assign(
          {
            contactId: 1,
            accountId: 'BBBBBB',
            accountType: 'BBBBBB',
            contactType: 'BBBBBB',
            contactPerson: 'BBBBBB',
            contactPhone: 'BBBBBB',
            contactEmail: 'BBBBBB',
            status: 'BBBBBB',
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

      it('should delete a CustContact', () => {
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
