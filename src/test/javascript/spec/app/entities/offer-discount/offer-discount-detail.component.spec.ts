import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { OfferDiscountDetailComponent } from 'app/entities/offer-discount/offer-discount-detail.component';
import { OfferDiscount } from 'app/shared/model/offer-discount.model';

describe('Component Tests', () => {
  describe('OfferDiscount Management Detail Component', () => {
    let comp: OfferDiscountDetailComponent;
    let fixture: ComponentFixture<OfferDiscountDetailComponent>;
    const route = ({ data: of({ offerDiscount: new OfferDiscount('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [OfferDiscountDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(OfferDiscountDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OfferDiscountDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load offerDiscount on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.offerDiscount).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
