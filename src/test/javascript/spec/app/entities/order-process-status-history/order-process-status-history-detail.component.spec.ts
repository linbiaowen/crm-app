import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { OrderProcessStatusHistoryDetailComponent } from 'app/entities/order-process-status-history/order-process-status-history-detail.component';
import { OrderProcessStatusHistory } from 'app/shared/model/order-process-status-history.model';

describe('Component Tests', () => {
  describe('OrderProcessStatusHistory Management Detail Component', () => {
    let comp: OrderProcessStatusHistoryDetailComponent;
    let fixture: ComponentFixture<OrderProcessStatusHistoryDetailComponent>;
    const route = ({ data: of({ orderProcessStatusHistory: new OrderProcessStatusHistory('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [OrderProcessStatusHistoryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(OrderProcessStatusHistoryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OrderProcessStatusHistoryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load orderProcessStatusHistory on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.orderProcessStatusHistory).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
