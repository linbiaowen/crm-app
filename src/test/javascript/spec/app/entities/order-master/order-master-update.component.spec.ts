import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { OrderMasterUpdateComponent } from 'app/entities/order-master/order-master-update.component';
import { OrderMasterService } from 'app/entities/order-master/order-master.service';
import { OrderMaster } from 'app/shared/model/order-master.model';

describe('Component Tests', () => {
  describe('OrderMaster Management Update Component', () => {
    let comp: OrderMasterUpdateComponent;
    let fixture: ComponentFixture<OrderMasterUpdateComponent>;
    let service: OrderMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [OrderMasterUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(OrderMasterUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrderMasterUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrderMasterService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OrderMaster('123');
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
        const entity = new OrderMaster();
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
