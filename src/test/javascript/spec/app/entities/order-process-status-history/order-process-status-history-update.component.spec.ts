import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { OrderProcessStatusHistoryUpdateComponent } from 'app/entities/order-process-status-history/order-process-status-history-update.component';
import { OrderProcessStatusHistoryService } from 'app/entities/order-process-status-history/order-process-status-history.service';
import { OrderProcessStatusHistory } from 'app/shared/model/order-process-status-history.model';

describe('Component Tests', () => {
  describe('OrderProcessStatusHistory Management Update Component', () => {
    let comp: OrderProcessStatusHistoryUpdateComponent;
    let fixture: ComponentFixture<OrderProcessStatusHistoryUpdateComponent>;
    let service: OrderProcessStatusHistoryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [OrderProcessStatusHistoryUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(OrderProcessStatusHistoryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrderProcessStatusHistoryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrderProcessStatusHistoryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OrderProcessStatusHistory('123');
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new OrderProcessStatusHistory();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
