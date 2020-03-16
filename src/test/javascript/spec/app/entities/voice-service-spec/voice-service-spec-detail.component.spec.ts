import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { VoiceServiceSpecDetailComponent } from 'app/entities/voice-service-spec/voice-service-spec-detail.component';
import { VoiceServiceSpec } from 'app/shared/model/voice-service-spec.model';

describe('Component Tests', () => {
  describe('VoiceServiceSpec Management Detail Component', () => {
    let comp: VoiceServiceSpecDetailComponent;
    let fixture: ComponentFixture<VoiceServiceSpecDetailComponent>;
    const route = ({ data: of({ voiceServiceSpec: new VoiceServiceSpec('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [VoiceServiceSpecDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(VoiceServiceSpecDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(VoiceServiceSpecDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load voiceServiceSpec on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.voiceServiceSpec).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
