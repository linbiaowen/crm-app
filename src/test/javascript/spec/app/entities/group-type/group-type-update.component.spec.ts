import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { GroupTypeUpdateComponent } from 'app/entities/group-type/group-type-update.component';
import { GroupTypeService } from 'app/entities/group-type/group-type.service';
import { GroupType } from 'app/shared/model/group-type.model';

describe('Component Tests', () => {
  describe('GroupType Management Update Component', () => {
    let comp: GroupTypeUpdateComponent;
    let fixture: ComponentFixture<GroupTypeUpdateComponent>;
    let service: GroupTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [GroupTypeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(GroupTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GroupTypeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GroupTypeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GroupType('123');
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
        const entity = new GroupType();
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
