import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { CustCommOptoutMasterUpdateComponent } from 'app/entities/cust-comm-optout-master/cust-comm-optout-master-update.component';
import { CustCommOptoutMasterService } from 'app/entities/cust-comm-optout-master/cust-comm-optout-master.service';
import { CustCommOptoutMaster } from 'app/shared/model/cust-comm-optout-master.model';

describe('Component Tests', () => {
  describe('CustCommOptoutMaster Management Update Component', () => {
    let comp: CustCommOptoutMasterUpdateComponent;
    let fixture: ComponentFixture<CustCommOptoutMasterUpdateComponent>;
    let service: CustCommOptoutMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [CustCommOptoutMasterUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CustCommOptoutMasterUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CustCommOptoutMasterUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CustCommOptoutMasterService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CustCommOptoutMaster('123');
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
        const entity = new CustCommOptoutMaster();
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
