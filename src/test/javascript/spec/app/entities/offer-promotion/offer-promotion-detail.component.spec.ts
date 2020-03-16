import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { OfferPromotionDetailComponent } from 'app/entities/offer-promotion/offer-promotion-detail.component';
import { OfferPromotion } from 'app/shared/model/offer-promotion.model';

describe('Component Tests', () => {
  describe('OfferPromotion Management Detail Component', () => {
    let comp: OfferPromotionDetailComponent;
    let fixture: ComponentFixture<OfferPromotionDetailComponent>;
    const route = ({ data: of({ offerPromotion: new OfferPromotion('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [OfferPromotionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(OfferPromotionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OfferPromotionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load offerPromotion on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.offerPromotion).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
