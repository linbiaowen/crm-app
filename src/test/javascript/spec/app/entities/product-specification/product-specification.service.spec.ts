import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ProductSpecificationService } from 'app/entities/product-specification/product-specification.service';
import { IProductSpecification, ProductSpecification } from 'app/shared/model/product-specification.model';
import { ProductSpecType } from 'app/shared/model/enumerations/product-spec-type.model';
import { SkuType } from 'app/shared/model/enumerations/sku-type.model';
import { SimType } from 'app/shared/model/enumerations/sim-type.model';
import { BoxType } from 'app/shared/model/enumerations/box-type.model';

describe('Service Tests', () => {
  describe('ProductSpecification Service', () => {
    let injector: TestBed;
    let service: ProductSpecificationService;
    let httpMock: HttpTestingController;
    let elemDefault: IProductSpecification;
    let expectedResult: IProductSpecification | IProductSpecification[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ProductSpecificationService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ProductSpecification(
        'ID',
        0,
        'AAAAAAA',
        'AAAAAAA',
        ProductSpecType.DEVICE,
        SkuType.DEVICE,
        SimType.PHYSICAL_SIM,
        BoxType.TRAVEL,
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

      it('should create a ProductSpecification', () => {
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

        service.create(new ProductSpecification()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ProductSpecification', () => {
        const returnedFromService = Object.assign(
          {
            productSpecId: 1,
            productId: 'BBBBBB',
            serviceSpecId: 'BBBBBB',
            productSpecType: 'BBBBBB',
            skuType: 'BBBBBB',
            simType: 'BBBBBB',
            boxType: 'BBBBBB',
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

      it('should return a list of ProductSpecification', () => {
        const returnedFromService = Object.assign(
          {
            productSpecId: 1,
            productId: 'BBBBBB',
            serviceSpecId: 'BBBBBB',
            productSpecType: 'BBBBBB',
            skuType: 'BBBBBB',
            simType: 'BBBBBB',
            boxType: 'BBBBBB',
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

      it('should delete a ProductSpecification', () => {
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
