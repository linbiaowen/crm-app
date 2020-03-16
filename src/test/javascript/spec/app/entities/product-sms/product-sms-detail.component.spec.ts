import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { ProductSmsDetailComponent } from 'app/entities/product-sms/product-sms-detail.component';
import { ProductSms } from 'app/shared/model/product-sms.model';

describe('Component Tests', () => {
  describe('ProductSms Management Detail Component', () => {
    let comp: ProductSmsDetailComponent;
    let fixture: ComponentFixture<ProductSmsDetailComponent>;
    const route = ({ data: of({ productSms: new ProductSms('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [ProductSmsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProductSmsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProductSmsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load productSms on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.productSms).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
