import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { ModelCategoryUpdateComponent } from 'app/entities/model-category/model-category-update.component';
import { ModelCategoryService } from 'app/entities/model-category/model-category.service';
import { ModelCategory } from 'app/shared/model/model-category.model';

describe('Component Tests', () => {
  describe('ModelCategory Management Update Component', () => {
    let comp: ModelCategoryUpdateComponent;
    let fixture: ComponentFixture<ModelCategoryUpdateComponent>;
    let service: ModelCategoryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [ModelCategoryUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ModelCategoryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ModelCategoryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ModelCategoryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ModelCategory('123');
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
        const entity = new ModelCategory();
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
