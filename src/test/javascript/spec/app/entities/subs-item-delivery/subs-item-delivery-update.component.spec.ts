import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { SubsItemDeliveryUpdateComponent } from 'app/entities/subs-item-delivery/subs-item-delivery-update.component';
import { SubsItemDeliveryService } from 'app/entities/subs-item-delivery/subs-item-delivery.service';
import { SubsItemDelivery } from 'app/shared/model/subs-item-delivery.model';

describe('Component Tests', () => {
  describe('SubsItemDelivery Management Update Component', () => {
    let comp: SubsItemDeliveryUpdateComponent;
    let fixture: ComponentFixture<SubsItemDeliveryUpdateComponent>;
    let service: SubsItemDeliveryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [SubsItemDeliveryUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SubsItemDeliveryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SubsItemDeliveryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SubsItemDeliveryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SubsItemDelivery('123');
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
        const entity = new SubsItemDelivery();
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
