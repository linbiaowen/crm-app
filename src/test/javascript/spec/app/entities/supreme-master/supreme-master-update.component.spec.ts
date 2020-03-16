import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { SupremeMasterUpdateComponent } from 'app/entities/supreme-master/supreme-master-update.component';
import { SupremeMasterService } from 'app/entities/supreme-master/supreme-master.service';
import { SupremeMaster } from 'app/shared/model/supreme-master.model';

describe('Component Tests', () => {
  describe('SupremeMaster Management Update Component', () => {
    let comp: SupremeMasterUpdateComponent;
    let fixture: ComponentFixture<SupremeMasterUpdateComponent>;
    let service: SupremeMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [SupremeMasterUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SupremeMasterUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SupremeMasterUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SupremeMasterService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SupremeMaster('123');
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
        const entity = new SupremeMaster();
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
