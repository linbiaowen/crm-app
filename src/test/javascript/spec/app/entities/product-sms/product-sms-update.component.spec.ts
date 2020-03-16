import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { ProductSmsUpdateComponent } from 'app/entities/product-sms/product-sms-update.component';
import { ProductSmsService } from 'app/entities/product-sms/product-sms.service';
import { ProductSms } from 'app/shared/model/product-sms.model';

describe('Component Tests', () => {
  describe('ProductSms Management Update Component', () => {
    let comp: ProductSmsUpdateComponent;
    let fixture: ComponentFixture<ProductSmsUpdateComponent>;
    let service: ProductSmsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [ProductSmsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProductSmsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProductSmsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProductSmsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProductSms('123');
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
        const entity = new ProductSms();
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
