import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { ModelGroupUpdateComponent } from 'app/entities/model-group/model-group-update.component';
import { ModelGroupService } from 'app/entities/model-group/model-group.service';
import { ModelGroup } from 'app/shared/model/model-group.model';

describe('Component Tests', () => {
  describe('ModelGroup Management Update Component', () => {
    let comp: ModelGroupUpdateComponent;
    let fixture: ComponentFixture<ModelGroupUpdateComponent>;
    let service: ModelGroupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [ModelGroupUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ModelGroupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ModelGroupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ModelGroupService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ModelGroup('123');
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
        const entity = new ModelGroup();
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
