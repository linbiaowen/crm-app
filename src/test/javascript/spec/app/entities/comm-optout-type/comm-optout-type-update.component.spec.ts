import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { CommOptoutTypeUpdateComponent } from 'app/entities/comm-optout-type/comm-optout-type-update.component';
import { CommOptoutTypeService } from 'app/entities/comm-optout-type/comm-optout-type.service';
import { CommOptoutType } from 'app/shared/model/comm-optout-type.model';

describe('Component Tests', () => {
  describe('CommOptoutType Management Update Component', () => {
    let comp: CommOptoutTypeUpdateComponent;
    let fixture: ComponentFixture<CommOptoutTypeUpdateComponent>;
    let service: CommOptoutTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [CommOptoutTypeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CommOptoutTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CommOptoutTypeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CommOptoutTypeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CommOptoutType('123');
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
        const entity = new CommOptoutType();
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
