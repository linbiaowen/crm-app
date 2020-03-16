import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { ProductDataDetailComponent } from 'app/entities/product-data/product-data-detail.component';
import { ProductData } from 'app/shared/model/product-data.model';

describe('Component Tests', () => {
  describe('ProductData Management Detail Component', () => {
    let comp: ProductDataDetailComponent;
    let fixture: ComponentFixture<ProductDataDetailComponent>;
    const route = ({ data: of({ productData: new ProductData('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [ProductDataDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProductDataDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProductDataDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load productData on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.productData).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
