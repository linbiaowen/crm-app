import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { ProductSimAttributesUpdateComponent } from 'app/entities/product-sim-attributes/product-sim-attributes-update.component';
import { ProductSimAttributesService } from 'app/entities/product-sim-attributes/product-sim-attributes.service';
import { ProductSimAttributes } from 'app/shared/model/product-sim-attributes.model';

describe('Component Tests', () => {
  describe('ProductSimAttributes Management Update Component', () => {
    let comp: ProductSimAttributesUpdateComponent;
    let fixture: ComponentFixture<ProductSimAttributesUpdateComponent>;
    let service: ProductSimAttributesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [ProductSimAttributesUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProductSimAttributesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProductSimAttributesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProductSimAttributesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProductSimAttributes('123');
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
        const entity = new ProductSimAttributes();
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
