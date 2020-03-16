import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { CustAddressUpdateComponent } from 'app/entities/cust-address/cust-address-update.component';
import { CustAddressService } from 'app/entities/cust-address/cust-address.service';
import { CustAddress } from 'app/shared/model/cust-address.model';

describe('Component Tests', () => {
  describe('CustAddress Management Update Component', () => {
    let comp: CustAddressUpdateComponent;
    let fixture: ComponentFixture<CustAddressUpdateComponent>;
    let service: CustAddressService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [CustAddressUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CustAddressUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CustAddressUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CustAddressService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CustAddress('123');
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
        const entity = new CustAddress();
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
