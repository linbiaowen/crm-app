import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { SubsOrderDetailsUpdateComponent } from 'app/entities/subs-order-details/subs-order-details-update.component';
import { SubsOrderDetailsService } from 'app/entities/subs-order-details/subs-order-details.service';
import { SubsOrderDetails } from 'app/shared/model/subs-order-details.model';

describe('Component Tests', () => {
  describe('SubsOrderDetails Management Update Component', () => {
    let comp: SubsOrderDetailsUpdateComponent;
    let fixture: ComponentFixture<SubsOrderDetailsUpdateComponent>;
    let service: SubsOrderDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [SubsOrderDetailsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SubsOrderDetailsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SubsOrderDetailsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SubsOrderDetailsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SubsOrderDetails('123');
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
        const entity = new SubsOrderDetails();
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
