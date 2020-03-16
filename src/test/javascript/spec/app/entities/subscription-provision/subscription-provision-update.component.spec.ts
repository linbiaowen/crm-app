import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { SubscriptionProvisionUpdateComponent } from 'app/entities/subscription-provision/subscription-provision-update.component';
import { SubscriptionProvisionService } from 'app/entities/subscription-provision/subscription-provision.service';
import { SubscriptionProvision } from 'app/shared/model/subscription-provision.model';

describe('Component Tests', () => {
  describe('SubscriptionProvision Management Update Component', () => {
    let comp: SubscriptionProvisionUpdateComponent;
    let fixture: ComponentFixture<SubscriptionProvisionUpdateComponent>;
    let service: SubscriptionProvisionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [SubscriptionProvisionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SubscriptionProvisionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SubscriptionProvisionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SubscriptionProvisionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SubscriptionProvision('123');
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
        const entity = new SubscriptionProvision();
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
