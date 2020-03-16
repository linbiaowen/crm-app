import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { ProductBoxTypeDetailComponent } from 'app/entities/product-box-type/product-box-type-detail.component';
import { ProductBoxType } from 'app/shared/model/product-box-type.model';

describe('Component Tests', () => {
  describe('ProductBoxType Management Detail Component', () => {
    let comp: ProductBoxTypeDetailComponent;
    let fixture: ComponentFixture<ProductBoxTypeDetailComponent>;
    const route = ({ data: of({ productBoxType: new ProductBoxType('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [ProductBoxTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProductBoxTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProductBoxTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load productBoxType on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.productBoxType).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
