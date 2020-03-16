import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { DeliveryMethodUpdateComponent } from 'app/entities/delivery-method/delivery-method-update.component';
import { DeliveryMethodService } from 'app/entities/delivery-method/delivery-method.service';
import { DeliveryMethod } from 'app/shared/model/delivery-method.model';

describe('Component Tests', () => {
  describe('DeliveryMethod Management Update Component', () => {
    let comp: DeliveryMethodUpdateComponent;
    let fixture: ComponentFixture<DeliveryMethodUpdateComponent>;
    let service: DeliveryMethodService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [DeliveryMethodUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DeliveryMethodUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DeliveryMethodUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DeliveryMethodService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DeliveryMethod('123');
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
        const entity = new DeliveryMethod();
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
