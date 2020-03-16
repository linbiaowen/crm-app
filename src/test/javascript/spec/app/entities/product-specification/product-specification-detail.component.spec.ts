import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { ProductSpecificationDetailComponent } from 'app/entities/product-specification/product-specification-detail.component';
import { ProductSpecification } from 'app/shared/model/product-specification.model';

describe('Component Tests', () => {
  describe('ProductSpecification Management Detail Component', () => {
    let comp: ProductSpecificationDetailComponent;
    let fixture: ComponentFixture<ProductSpecificationDetailComponent>;
    const route = ({ data: of({ productSpecification: new ProductSpecification('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [ProductSpecificationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProductSpecificationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProductSpecificationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load productSpecification on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.productSpecification).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
