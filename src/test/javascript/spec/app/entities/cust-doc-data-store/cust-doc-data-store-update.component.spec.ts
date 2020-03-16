import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { CustDocDataStoreUpdateComponent } from 'app/entities/cust-doc-data-store/cust-doc-data-store-update.component';
import { CustDocDataStoreService } from 'app/entities/cust-doc-data-store/cust-doc-data-store.service';
import { CustDocDataStore } from 'app/shared/model/cust-doc-data-store.model';

describe('Component Tests', () => {
  describe('CustDocDataStore Management Update Component', () => {
    let comp: CustDocDataStoreUpdateComponent;
    let fixture: ComponentFixture<CustDocDataStoreUpdateComponent>;
    let service: CustDocDataStoreService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [CustDocDataStoreUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CustDocDataStoreUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CustDocDataStoreUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CustDocDataStoreService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CustDocDataStore('123');
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
        const entity = new CustDocDataStore();
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
