import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { OfferAdvancePaymentDetailComponent } from 'app/entities/offer-advance-payment/offer-advance-payment-detail.component';
import { OfferAdvancePayment } from 'app/shared/model/offer-advance-payment.model';

describe('Component Tests', () => {
  describe('OfferAdvancePayment Management Detail Component', () => {
    let comp: OfferAdvancePaymentDetailComponent;
    let fixture: ComponentFixture<OfferAdvancePaymentDetailComponent>;
    const route = ({ data: of({ offerAdvancePayment: new OfferAdvancePayment('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [OfferAdvancePaymentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(OfferAdvancePaymentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OfferAdvancePaymentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load offerAdvancePayment on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.offerAdvancePayment).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
