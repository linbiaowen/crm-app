import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { CrmwebTestModule } from '../../../test.module';
import { VoiceServiceSpecComponent } from 'app/entities/voice-service-spec/voice-service-spec.component';
import { VoiceServiceSpecService } from 'app/entities/voice-service-spec/voice-service-spec.service';
import { VoiceServiceSpec } from 'app/shared/model/voice-service-spec.model';

describe('Component Tests', () => {
  describe('VoiceServiceSpec Management Component', () => {
    let comp: VoiceServiceSpecComponent;
    let fixture: ComponentFixture<VoiceServiceSpecComponent>;
    let service: VoiceServiceSpecService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [VoiceServiceSpecComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: {
                subscribe: (fn: (value: Data) => void) =>
                  fn({
                    pagingParams: {
                      predicate: 'id',
                      reverse: false,
                      page: 0
                    }
                  })
              }
            }
          }
        ]
      })
        .overrideTemplate(VoiceServiceSpecComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(VoiceServiceSpecComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(VoiceServiceSpecService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new VoiceServiceSpec('123')],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.voiceServiceSpecs && comp.voiceServiceSpecs[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new VoiceServiceSpec('123')],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.voiceServiceSpecs && comp.voiceServiceSpecs[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
