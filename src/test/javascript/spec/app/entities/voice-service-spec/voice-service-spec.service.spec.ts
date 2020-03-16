import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { VoiceServiceSpecService } from 'app/entities/voice-service-spec/voice-service-spec.service';
import { IVoiceServiceSpec, VoiceServiceSpec } from 'app/shared/model/voice-service-spec.model';

describe('Service Tests', () => {
  describe('VoiceServiceSpec Service', () => {
    let injector: TestBed;
    let service: VoiceServiceSpecService;
    let httpMock: HttpTestingController;
    let elemDefault: IVoiceServiceSpec;
    let expectedResult: IVoiceServiceSpec | IVoiceServiceSpec[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(VoiceServiceSpecService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new VoiceServiceSpec(
        'ID',
        0,
        'AAAAAAA',
        false,
        false,
        false,
        'AAAAAAA',
        false,
        false,
        false,
        false,
        false,
        'AAAAAAA',
        false,
        false,
        false,
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

      it('should create a VoiceServiceSpec', () => {
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

        service.create(new VoiceServiceSpec()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a VoiceServiceSpec', () => {
        const returnedFromService = Object.assign(
          {
            voiceSpecId: 1,
            serviceSpecId: 'BBBBBB',
            roamingIncomingEnabled: true,
            roamingOutgoingEnabled: true,
            iddEnabled: true,
            iddOptions: 'BBBBBB',
            callForwardEnabled: true,
            callWaitingEnabled: true,
            clipEnabled: true,
            callBarringEnabled: true,
            mvrsEnabled: true,
            specialCallServices: 'BBBBBB',
            callRoutingSupported: true,
            prbtSupported: true,
            hrbtSupported: true,
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

      it('should return a list of VoiceServiceSpec', () => {
        const returnedFromService = Object.assign(
          {
            voiceSpecId: 1,
            serviceSpecId: 'BBBBBB',
            roamingIncomingEnabled: true,
            roamingOutgoingEnabled: true,
            iddEnabled: true,
            iddOptions: 'BBBBBB',
            callForwardEnabled: true,
            callWaitingEnabled: true,
            clipEnabled: true,
            callBarringEnabled: true,
            mvrsEnabled: true,
            specialCallServices: 'BBBBBB',
            callRoutingSupported: true,
            prbtSupported: true,
            hrbtSupported: true,
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

      it('should delete a VoiceServiceSpec', () => {
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
