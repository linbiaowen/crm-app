import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { DeliveryOptionUpdateComponent } from 'app/entities/delivery-option/delivery-option-update.component';
import { DeliveryOptionService } from 'app/entities/delivery-option/delivery-option.service';
import { DeliveryOption } from 'app/shared/model/delivery-option.model';

describe('Component Tests', () => {
  describe('DeliveryOption Management Update Component', () => {
    let comp: DeliveryOptionUpdateComponent;
    let fixture: ComponentFixture<DeliveryOptionUpdateComponent>;
    let service: DeliveryOptionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [DeliveryOptionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DeliveryOptionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DeliveryOptionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DeliveryOptionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DeliveryOption('123');
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
        const entity = new DeliveryOption();
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
