import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { OrderProcessStatusUpdateComponent } from 'app/entities/order-process-status/order-process-status-update.component';
import { OrderProcessStatusService } from 'app/entities/order-process-status/order-process-status.service';
import { OrderProcessStatus } from 'app/shared/model/order-process-status.model';

describe('Component Tests', () => {
  describe('OrderProcessStatus Management Update Component', () => {
    let comp: OrderProcessStatusUpdateComponent;
    let fixture: ComponentFixture<OrderProcessStatusUpdateComponent>;
    let service: OrderProcessStatusService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [OrderProcessStatusUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(OrderProcessStatusUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrderProcessStatusUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrderProcessStatusService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OrderProcessStatus('123');
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
        const entity = new OrderProcessStatus();
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
