import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { BillCycleUpdateComponent } from 'app/entities/bill-cycle/bill-cycle-update.component';
import { BillCycleService } from 'app/entities/bill-cycle/bill-cycle.service';
import { BillCycle } from 'app/shared/model/bill-cycle.model';

describe('Component Tests', () => {
  describe('BillCycle Management Update Component', () => {
    let comp: BillCycleUpdateComponent;
    let fixture: ComponentFixture<BillCycleUpdateComponent>;
    let service: BillCycleService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [BillCycleUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(BillCycleUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BillCycleUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BillCycleService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BillCycle('123');
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
        const entity = new BillCycle();
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
