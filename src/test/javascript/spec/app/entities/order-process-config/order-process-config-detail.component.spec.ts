import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { OrderProcessConfigDetailComponent } from 'app/entities/order-process-config/order-process-config-detail.component';
import { OrderProcessConfig } from 'app/shared/model/order-process-config.model';

describe('Component Tests', () => {
  describe('OrderProcessConfig Management Detail Component', () => {
    let comp: OrderProcessConfigDetailComponent;
    let fixture: ComponentFixture<OrderProcessConfigDetailComponent>;
    const route = ({ data: of({ orderProcessConfig: new OrderProcessConfig('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [OrderProcessConfigDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(OrderProcessConfigDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OrderProcessConfigDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load orderProcessConfig on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.orderProcessConfig).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
