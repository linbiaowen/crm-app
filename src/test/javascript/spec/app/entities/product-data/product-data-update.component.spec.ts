import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { ProductDataUpdateComponent } from 'app/entities/product-data/product-data-update.component';
import { ProductDataService } from 'app/entities/product-data/product-data.service';
import { ProductData } from 'app/shared/model/product-data.model';

describe('Component Tests', () => {
  describe('ProductData Management Update Component', () => {
    let comp: ProductDataUpdateComponent;
    let fixture: ComponentFixture<ProductDataUpdateComponent>;
    let service: ProductDataService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [ProductDataUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProductDataUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProductDataUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProductDataService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProductData('123');
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
        const entity = new ProductData();
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
