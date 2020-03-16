import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { OrderProcessStatusDetailComponent } from 'app/entities/order-process-status/order-process-status-detail.component';
import { OrderProcessStatus } from 'app/shared/model/order-process-status.model';

describe('Component Tests', () => {
  describe('OrderProcessStatus Management Detail Component', () => {
    let comp: OrderProcessStatusDetailComponent;
    let fixture: ComponentFixture<OrderProcessStatusDetailComponent>;
    const route = ({ data: of({ orderProcessStatus: new OrderProcessStatus('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [OrderProcessStatusDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(OrderProcessStatusDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OrderProcessStatusDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load orderProcessStatus on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.orderProcessStatus).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
