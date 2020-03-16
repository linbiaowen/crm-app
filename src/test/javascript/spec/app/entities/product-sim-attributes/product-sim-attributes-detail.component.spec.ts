import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { ProductSimAttributesDetailComponent } from 'app/entities/product-sim-attributes/product-sim-attributes-detail.component';
import { ProductSimAttributes } from 'app/shared/model/product-sim-attributes.model';

describe('Component Tests', () => {
  describe('ProductSimAttributes Management Detail Component', () => {
    let comp: ProductSimAttributesDetailComponent;
    let fixture: ComponentFixture<ProductSimAttributesDetailComponent>;
    const route = ({ data: of({ productSimAttributes: new ProductSimAttributes('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [ProductSimAttributesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProductSimAttributesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProductSimAttributesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load productSimAttributes on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.productSimAttributes).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
