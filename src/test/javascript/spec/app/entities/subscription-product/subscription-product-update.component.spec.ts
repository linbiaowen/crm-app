import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { SubscriptionProductUpdateComponent } from 'app/entities/subscription-product/subscription-product-update.component';
import { SubscriptionProductService } from 'app/entities/subscription-product/subscription-product.service';
import { SubscriptionProduct } from 'app/shared/model/subscription-product.model';

describe('Component Tests', () => {
  describe('SubscriptionProduct Management Update Component', () => {
    let comp: SubscriptionProductUpdateComponent;
    let fixture: ComponentFixture<SubscriptionProductUpdateComponent>;
    let service: SubscriptionProductService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [SubscriptionProductUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SubscriptionProductUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SubscriptionProductUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SubscriptionProductService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SubscriptionProduct('123');
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
        const entity = new SubscriptionProduct();
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
