import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { SubsPurchaseControlUpdateComponent } from 'app/entities/subs-purchase-control/subs-purchase-control-update.component';
import { SubsPurchaseControlService } from 'app/entities/subs-purchase-control/subs-purchase-control.service';
import { SubsPurchaseControl } from 'app/shared/model/subs-purchase-control.model';

describe('Component Tests', () => {
  describe('SubsPurchaseControl Management Update Component', () => {
    let comp: SubsPurchaseControlUpdateComponent;
    let fixture: ComponentFixture<SubsPurchaseControlUpdateComponent>;
    let service: SubsPurchaseControlService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [SubsPurchaseControlUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SubsPurchaseControlUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SubsPurchaseControlUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SubsPurchaseControlService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SubsPurchaseControl('123');
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
        const entity = new SubsPurchaseControl();
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
