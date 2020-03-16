import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ModelGroupService } from 'app/entities/model-group/model-group.service';
import { IModelGroup, ModelGroup } from 'app/shared/model/model-group.model';

describe('Service Tests', () => {
  describe('ModelGroup Service', () => {
    let injector: TestBed;
    let service: ModelGroupService;
    let httpMock: HttpTestingController;
    let elemDefault: IModelGroup;
    let expectedResult: IModelGroup | IModelGroup[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ModelGroupService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ModelGroup(
        'ID',
        'AAAAAAA',
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

      it('should create a ModelGroup', () => {
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

        service.create(new ModelGroup()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ModelGroup', () => {
        const returnedFromService = Object.assign(
          {
            modelGroup: 'BBBBBB',
            groupDesc: 'BBBBBB',
            listPrice: 1,
            brand: 'BBBBBB',
            model: 'BBBBBB',
            origCountry: 'BBBBBB',
            network: 'BBBBBB',
            camera: 'BBBBBB',
            memCardSlot: 'BBBBBB',
            dataTransfer: 'BBBBBB',
            warranty: 'BBBBBB',
            warrantyProvider: 'BBBBBB',
            modelCate: 'BBBBBB',
            remarks: 'BBBBBB',
            remarksEndDate: 'BBBBBB',
            brandChi: 'BBBBBB',
            modelChi: 'BBBBBB',
            origCountryChi: 'BBBBBB',
            networkChi: 'BBBBBB',
            cameraChi: 'BBBBBB',
            memCardSlotChi: 'BBBBBB',
            dataTransferChi: 'BBBBBB',
            warrantyChi: 'BBBBBB',
            warrantyProviderChi: 'BBBBBB',
            modelCateChi: 'BBBBBB',
            remarksChi: 'BBBBBB',
            remarksChiEndDate: 'BBBBBB',
            couponFlag: 'BBBBBB',
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

      it('should return a list of ModelGroup', () => {
        const returnedFromService = Object.assign(
          {
            modelGroup: 'BBBBBB',
            groupDesc: 'BBBBBB',
            listPrice: 1,
            brand: 'BBBBBB',
            model: 'BBBBBB',
            origCountry: 'BBBBBB',
            network: 'BBBBBB',
            camera: 'BBBBBB',
            memCardSlot: 'BBBBBB',
            dataTransfer: 'BBBBBB',
            warranty: 'BBBBBB',
            warrantyProvider: 'BBBBBB',
            modelCate: 'BBBBBB',
            remarks: 'BBBBBB',
            remarksEndDate: 'BBBBBB',
            brandChi: 'BBBBBB',
            modelChi: 'BBBBBB',
            origCountryChi: 'BBBBBB',
            networkChi: 'BBBBBB',
            cameraChi: 'BBBBBB',
            memCardSlotChi: 'BBBBBB',
            dataTransferChi: 'BBBBBB',
            warrantyChi: 'BBBBBB',
            warrantyProviderChi: 'BBBBBB',
            modelCateChi: 'BBBBBB',
            remarksChi: 'BBBBBB',
            remarksChiEndDate: 'BBBBBB',
            couponFlag: 'BBBBBB',
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

      it('should delete a ModelGroup', () => {
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
