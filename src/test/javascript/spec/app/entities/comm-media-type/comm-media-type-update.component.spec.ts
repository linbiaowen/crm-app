import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { CommMediaTypeUpdateComponent } from 'app/entities/comm-media-type/comm-media-type-update.component';
import { CommMediaTypeService } from 'app/entities/comm-media-type/comm-media-type.service';
import { CommMediaType } from 'app/shared/model/comm-media-type.model';

describe('Component Tests', () => {
  describe('CommMediaType Management Update Component', () => {
    let comp: CommMediaTypeUpdateComponent;
    let fixture: ComponentFixture<CommMediaTypeUpdateComponent>;
    let service: CommMediaTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [CommMediaTypeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CommMediaTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CommMediaTypeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CommMediaTypeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CommMediaType('123');
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
        const entity = new CommMediaType();
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
