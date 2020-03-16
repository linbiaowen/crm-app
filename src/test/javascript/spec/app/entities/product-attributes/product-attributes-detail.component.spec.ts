import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { ProductAttributesDetailComponent } from 'app/entities/product-attributes/product-attributes-detail.component';
import { ProductAttributes } from 'app/shared/model/product-attributes.model';

describe('Component Tests', () => {
  describe('ProductAttributes Management Detail Component', () => {
    let comp: ProductAttributesDetailComponent;
    let fixture: ComponentFixture<ProductAttributesDetailComponent>;
    const route = ({ data: of({ productAttributes: new ProductAttributes('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [ProductAttributesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProductAttributesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProductAttributesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load productAttributes on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.productAttributes).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
