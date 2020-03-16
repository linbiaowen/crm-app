import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { SimInventoryUpdateComponent } from 'app/entities/sim-inventory/sim-inventory-update.component';
import { SimInventoryService } from 'app/entities/sim-inventory/sim-inventory.service';
import { SimInventory } from 'app/shared/model/sim-inventory.model';

describe('Component Tests', () => {
  describe('SimInventory Management Update Component', () => {
    let comp: SimInventoryUpdateComponent;
    let fixture: ComponentFixture<SimInventoryUpdateComponent>;
    let service: SimInventoryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [SimInventoryUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SimInventoryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SimInventoryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SimInventoryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SimInventory('123');
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
        const entity = new SimInventory();
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
