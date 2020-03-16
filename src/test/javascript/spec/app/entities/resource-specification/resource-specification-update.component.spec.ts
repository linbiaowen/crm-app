import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { ResourceSpecificationUpdateComponent } from 'app/entities/resource-specification/resource-specification-update.component';
import { ResourceSpecificationService } from 'app/entities/resource-specification/resource-specification.service';
import { ResourceSpecification } from 'app/shared/model/resource-specification.model';

describe('Component Tests', () => {
  describe('ResourceSpecification Management Update Component', () => {
    let comp: ResourceSpecificationUpdateComponent;
    let fixture: ComponentFixture<ResourceSpecificationUpdateComponent>;
    let service: ResourceSpecificationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [ResourceSpecificationUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ResourceSpecificationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ResourceSpecificationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ResourceSpecificationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ResourceSpecification('123');
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
        const entity = new ResourceSpecification();
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
