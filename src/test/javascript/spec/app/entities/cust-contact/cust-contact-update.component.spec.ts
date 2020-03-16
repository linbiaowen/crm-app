import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { CustContactUpdateComponent } from 'app/entities/cust-contact/cust-contact-update.component';
import { CustContactService } from 'app/entities/cust-contact/cust-contact.service';
import { CustContact } from 'app/shared/model/cust-contact.model';

describe('Component Tests', () => {
  describe('CustContact Management Update Component', () => {
    let comp: CustContactUpdateComponent;
    let fixture: ComponentFixture<CustContactUpdateComponent>;
    let service: CustContactService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [CustContactUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CustContactUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CustContactUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CustContactService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CustContact('123');
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
        const entity = new CustContact();
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
