import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { CustDocumentUpdateComponent } from 'app/entities/cust-document/cust-document-update.component';
import { CustDocumentService } from 'app/entities/cust-document/cust-document.service';
import { CustDocument } from 'app/shared/model/cust-document.model';

describe('Component Tests', () => {
  describe('CustDocument Management Update Component', () => {
    let comp: CustDocumentUpdateComponent;
    let fixture: ComponentFixture<CustDocumentUpdateComponent>;
    let service: CustDocumentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [CustDocumentUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CustDocumentUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CustDocumentUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CustDocumentService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CustDocument('123');
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
        const entity = new CustDocument();
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
