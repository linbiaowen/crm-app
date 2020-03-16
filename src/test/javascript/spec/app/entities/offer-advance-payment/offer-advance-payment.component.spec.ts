import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { CrmwebTestModule } from '../../../test.module';
import { OfferAdvancePaymentComponent } from 'app/entities/offer-advance-payment/offer-advance-payment.component';
import { OfferAdvancePaymentService } from 'app/entities/offer-advance-payment/offer-advance-payment.service';
import { OfferAdvancePayment } from 'app/shared/model/offer-advance-payment.model';

describe('Component Tests', () => {
  describe('OfferAdvancePayment Management Component', () => {
    let comp: OfferAdvancePaymentComponent;
    let fixture: ComponentFixture<OfferAdvancePaymentComponent>;
    let service: OfferAdvancePaymentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [OfferAdvancePaymentComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: {
                subscribe: (fn: (value: Data) => void) =>
                  fn({
                    pagingParams: {
                      predicate: 'id',
                      reverse: false,
                      page: 0
                    }
                  })
              }
            }
          }
        ]
      })
        .overrideTemplate(OfferAdvancePaymentComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OfferAdvancePaymentComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OfferAdvancePaymentService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new OfferAdvancePayment('123')],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.offerAdvancePayments && comp.offerAdvancePayments[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new OfferAdvancePayment('123')],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.offerAdvancePayments && comp.offerAdvancePayments[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
