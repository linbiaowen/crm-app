import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { DataServiceSpecUpdateComponent } from 'app/entities/data-service-spec/data-service-spec-update.component';
import { DataServiceSpecService } from 'app/entities/data-service-spec/data-service-spec.service';
import { DataServiceSpec } from 'app/shared/model/data-service-spec.model';

describe('Component Tests', () => {
  describe('DataServiceSpec Management Update Component', () => {
    let comp: DataServiceSpecUpdateComponent;
    let fixture: ComponentFixture<DataServiceSpecUpdateComponent>;
    let service: DataServiceSpecService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [DataServiceSpecUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DataServiceSpecUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DataServiceSpecUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DataServiceSpecService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DataServiceSpec('123');
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
        const entity = new DataServiceSpec();
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
