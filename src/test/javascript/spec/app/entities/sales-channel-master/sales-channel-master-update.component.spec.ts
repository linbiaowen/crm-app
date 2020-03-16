import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { SalesChannelMasterUpdateComponent } from 'app/entities/sales-channel-master/sales-channel-master-update.component';
import { SalesChannelMasterService } from 'app/entities/sales-channel-master/sales-channel-master.service';
import { SalesChannelMaster } from 'app/shared/model/sales-channel-master.model';

describe('Component Tests', () => {
  describe('SalesChannelMaster Management Update Component', () => {
    let comp: SalesChannelMasterUpdateComponent;
    let fixture: ComponentFixture<SalesChannelMasterUpdateComponent>;
    let service: SalesChannelMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [SalesChannelMasterUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SalesChannelMasterUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SalesChannelMasterUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SalesChannelMasterService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SalesChannelMaster('123');
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
        const entity = new SalesChannelMaster();
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
