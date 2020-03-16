import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { ProductBoxTypeUpdateComponent } from 'app/entities/product-box-type/product-box-type-update.component';
import { ProductBoxTypeService } from 'app/entities/product-box-type/product-box-type.service';
import { ProductBoxType } from 'app/shared/model/product-box-type.model';

describe('Component Tests', () => {
  describe('ProductBoxType Management Update Component', () => {
    let comp: ProductBoxTypeUpdateComponent;
    let fixture: ComponentFixture<ProductBoxTypeUpdateComponent>;
    let service: ProductBoxTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [ProductBoxTypeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProductBoxTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProductBoxTypeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProductBoxTypeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProductBoxType('123');
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
        const entity = new ProductBoxType();
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
