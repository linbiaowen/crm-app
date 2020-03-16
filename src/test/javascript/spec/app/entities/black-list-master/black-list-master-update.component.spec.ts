import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { BlackListMasterUpdateComponent } from 'app/entities/black-list-master/black-list-master-update.component';
import { BlackListMasterService } from 'app/entities/black-list-master/black-list-master.service';
import { BlackListMaster } from 'app/shared/model/black-list-master.model';

describe('Component Tests', () => {
  describe('BlackListMaster Management Update Component', () => {
    let comp: BlackListMasterUpdateComponent;
    let fixture: ComponentFixture<BlackListMasterUpdateComponent>;
    let service: BlackListMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [BlackListMasterUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(BlackListMasterUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BlackListMasterUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BlackListMasterService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BlackListMaster('123');
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
        const entity = new BlackListMaster();
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
