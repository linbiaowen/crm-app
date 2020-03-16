import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { GroupEndReasonUpdateComponent } from 'app/entities/group-end-reason/group-end-reason-update.component';
import { GroupEndReasonService } from 'app/entities/group-end-reason/group-end-reason.service';
import { GroupEndReason } from 'app/shared/model/group-end-reason.model';

describe('Component Tests', () => {
  describe('GroupEndReason Management Update Component', () => {
    let comp: GroupEndReasonUpdateComponent;
    let fixture: ComponentFixture<GroupEndReasonUpdateComponent>;
    let service: GroupEndReasonService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [GroupEndReasonUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(GroupEndReasonUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GroupEndReasonUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GroupEndReasonService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GroupEndReason('123');
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
        const entity = new GroupEndReason();
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
