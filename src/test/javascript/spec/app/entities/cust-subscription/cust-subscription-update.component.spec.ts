import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { CustSubscriptionUpdateComponent } from 'app/entities/cust-subscription/cust-subscription-update.component';
import { CustSubscriptionService } from 'app/entities/cust-subscription/cust-subscription.service';
import { CustSubscription } from 'app/shared/model/cust-subscription.model';

describe('Component Tests', () => {
  describe('CustSubscription Management Update Component', () => {
    let comp: CustSubscriptionUpdateComponent;
    let fixture: ComponentFixture<CustSubscriptionUpdateComponent>;
    let service: CustSubscriptionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [CustSubscriptionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CustSubscriptionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CustSubscriptionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CustSubscriptionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CustSubscription('123');
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
        const entity = new CustSubscription();
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
