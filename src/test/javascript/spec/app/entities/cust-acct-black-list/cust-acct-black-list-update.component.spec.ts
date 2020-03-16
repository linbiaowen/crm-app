import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { CustAcctBlackListUpdateComponent } from 'app/entities/cust-acct-black-list/cust-acct-black-list-update.component';
import { CustAcctBlackListService } from 'app/entities/cust-acct-black-list/cust-acct-black-list.service';
import { CustAcctBlackList } from 'app/shared/model/cust-acct-black-list.model';

describe('Component Tests', () => {
  describe('CustAcctBlackList Management Update Component', () => {
    let comp: CustAcctBlackListUpdateComponent;
    let fixture: ComponentFixture<CustAcctBlackListUpdateComponent>;
    let service: CustAcctBlackListService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [CustAcctBlackListUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CustAcctBlackListUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CustAcctBlackListUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CustAcctBlackListService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CustAcctBlackList('123');
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
        const entity = new CustAcctBlackList();
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
