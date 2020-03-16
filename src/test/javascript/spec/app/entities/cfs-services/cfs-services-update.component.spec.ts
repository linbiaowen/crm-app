import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { CfsServicesUpdateComponent } from 'app/entities/cfs-services/cfs-services-update.component';
import { CfsServicesService } from 'app/entities/cfs-services/cfs-services.service';
import { CfsServices } from 'app/shared/model/cfs-services.model';

describe('Component Tests', () => {
  describe('CfsServices Management Update Component', () => {
    let comp: CfsServicesUpdateComponent;
    let fixture: ComponentFixture<CfsServicesUpdateComponent>;
    let service: CfsServicesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [CfsServicesUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CfsServicesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CfsServicesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CfsServicesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CfsServices('123');
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
        const entity = new CfsServices();
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
