import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { SubscriptionGroupUpdateComponent } from 'app/entities/subscription-group/subscription-group-update.component';
import { SubscriptionGroupService } from 'app/entities/subscription-group/subscription-group.service';
import { SubscriptionGroup } from 'app/shared/model/subscription-group.model';

describe('Component Tests', () => {
  describe('SubscriptionGroup Management Update Component', () => {
    let comp: SubscriptionGroupUpdateComponent;
    let fixture: ComponentFixture<SubscriptionGroupUpdateComponent>;
    let service: SubscriptionGroupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [SubscriptionGroupUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SubscriptionGroupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SubscriptionGroupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SubscriptionGroupService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SubscriptionGroup('123');
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
        const entity = new SubscriptionGroup();
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
