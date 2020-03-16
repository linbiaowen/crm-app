import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { OrderProcessConfigUpdateComponent } from 'app/entities/order-process-config/order-process-config-update.component';
import { OrderProcessConfigService } from 'app/entities/order-process-config/order-process-config.service';
import { OrderProcessConfig } from 'app/shared/model/order-process-config.model';

describe('Component Tests', () => {
  describe('OrderProcessConfig Management Update Component', () => {
    let comp: OrderProcessConfigUpdateComponent;
    let fixture: ComponentFixture<OrderProcessConfigUpdateComponent>;
    let service: OrderProcessConfigService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [OrderProcessConfigUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(OrderProcessConfigUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrderProcessConfigUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrderProcessConfigService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OrderProcessConfig('123');
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
        const entity = new OrderProcessConfig();
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
