import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { VoiceServiceSpecUpdateComponent } from 'app/entities/voice-service-spec/voice-service-spec-update.component';
import { VoiceServiceSpecService } from 'app/entities/voice-service-spec/voice-service-spec.service';
import { VoiceServiceSpec } from 'app/shared/model/voice-service-spec.model';

describe('Component Tests', () => {
  describe('VoiceServiceSpec Management Update Component', () => {
    let comp: VoiceServiceSpecUpdateComponent;
    let fixture: ComponentFixture<VoiceServiceSpecUpdateComponent>;
    let service: VoiceServiceSpecService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [VoiceServiceSpecUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(VoiceServiceSpecUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(VoiceServiceSpecUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(VoiceServiceSpecService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new VoiceServiceSpec('123');
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
        const entity = new VoiceServiceSpec();
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
