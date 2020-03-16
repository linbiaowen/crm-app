import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { SubsDeliveryItemDetailsUpdateComponent } from 'app/entities/subs-delivery-item-details/subs-delivery-item-details-update.component';
import { SubsDeliveryItemDetailsService } from 'app/entities/subs-delivery-item-details/subs-delivery-item-details.service';
import { SubsDeliveryItemDetails } from 'app/shared/model/subs-delivery-item-details.model';

describe('Component Tests', () => {
  describe('SubsDeliveryItemDetails Management Update Component', () => {
    let comp: SubsDeliveryItemDetailsUpdateComponent;
    let fixture: ComponentFixture<SubsDeliveryItemDetailsUpdateComponent>;
    let service: SubsDeliveryItemDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [SubsDeliveryItemDetailsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SubsDeliveryItemDetailsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SubsDeliveryItemDetailsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SubsDeliveryItemDetailsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SubsDeliveryItemDetails('123');
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
        const entity = new SubsDeliveryItemDetails();
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
