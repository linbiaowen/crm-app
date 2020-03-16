import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { ProductVoiceUpdateComponent } from 'app/entities/product-voice/product-voice-update.component';
import { ProductVoiceService } from 'app/entities/product-voice/product-voice.service';
import { ProductVoice } from 'app/shared/model/product-voice.model';

describe('Component Tests', () => {
  describe('ProductVoice Management Update Component', () => {
    let comp: ProductVoiceUpdateComponent;
    let fixture: ComponentFixture<ProductVoiceUpdateComponent>;
    let service: ProductVoiceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [ProductVoiceUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProductVoiceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProductVoiceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProductVoiceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProductVoice('123');
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
        const entity = new ProductVoice();
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
