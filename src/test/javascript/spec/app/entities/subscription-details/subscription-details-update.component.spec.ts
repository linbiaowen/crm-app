import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { SubscriptionDetailsUpdateComponent } from 'app/entities/subscription-details/subscription-details-update.component';
import { SubscriptionDetailsService } from 'app/entities/subscription-details/subscription-details.service';
import { SubscriptionDetails } from 'app/shared/model/subscription-details.model';

describe('Component Tests', () => {
  describe('SubscriptionDetails Management Update Component', () => {
    let comp: SubscriptionDetailsUpdateComponent;
    let fixture: ComponentFixture<SubscriptionDetailsUpdateComponent>;
    let service: SubscriptionDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [SubscriptionDetailsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SubscriptionDetailsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SubscriptionDetailsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SubscriptionDetailsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SubscriptionDetails('123');
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
        const entity = new SubscriptionDetails();
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
