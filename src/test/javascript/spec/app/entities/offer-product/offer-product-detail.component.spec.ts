import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { OfferProductDetailComponent } from 'app/entities/offer-product/offer-product-detail.component';
import { OfferProduct } from 'app/shared/model/offer-product.model';

describe('Component Tests', () => {
  describe('OfferProduct Management Detail Component', () => {
    let comp: OfferProductDetailComponent;
    let fixture: ComponentFixture<OfferProductDetailComponent>;
    const route = ({ data: of({ offerProduct: new OfferProduct('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [OfferProductDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(OfferProductDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OfferProductDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load offerProduct on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.offerProduct).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
