import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { ProductAttributesUpdateComponent } from 'app/entities/product-attributes/product-attributes-update.component';
import { ProductAttributesService } from 'app/entities/product-attributes/product-attributes.service';
import { ProductAttributes } from 'app/shared/model/product-attributes.model';

describe('Component Tests', () => {
  describe('ProductAttributes Management Update Component', () => {
    let comp: ProductAttributesUpdateComponent;
    let fixture: ComponentFixture<ProductAttributesUpdateComponent>;
    let service: ProductAttributesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [ProductAttributesUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProductAttributesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProductAttributesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProductAttributesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProductAttributes('123');
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
        const entity = new ProductAttributes();
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
