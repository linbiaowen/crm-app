import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { CfsServiceSpecUpdateComponent } from 'app/entities/cfs-service-spec/cfs-service-spec-update.component';
import { CfsServiceSpecService } from 'app/entities/cfs-service-spec/cfs-service-spec.service';
import { CfsServiceSpec } from 'app/shared/model/cfs-service-spec.model';

describe('Component Tests', () => {
  describe('CfsServiceSpec Management Update Component', () => {
    let comp: CfsServiceSpecUpdateComponent;
    let fixture: ComponentFixture<CfsServiceSpecUpdateComponent>;
    let service: CfsServiceSpecService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [CfsServiceSpecUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CfsServiceSpecUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CfsServiceSpecUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CfsServiceSpecService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CfsServiceSpec('123');
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
        const entity = new CfsServiceSpec();
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
