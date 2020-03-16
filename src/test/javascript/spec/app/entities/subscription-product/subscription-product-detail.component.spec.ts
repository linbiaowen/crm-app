import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { SubscriptionProductDetailComponent } from 'app/entities/subscription-product/subscription-product-detail.component';
import { SubscriptionProduct } from 'app/shared/model/subscription-product.model';

describe('Component Tests', () => {
  describe('SubscriptionProduct Management Detail Component', () => {
    let comp: SubscriptionProductDetailComponent;
    let fixture: ComponentFixture<SubscriptionProductDetailComponent>;
    const route = ({ data: of({ subscriptionProduct: new SubscriptionProduct('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [SubscriptionProductDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SubscriptionProductDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SubscriptionProductDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load subscriptionProduct on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.subscriptionProduct).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
