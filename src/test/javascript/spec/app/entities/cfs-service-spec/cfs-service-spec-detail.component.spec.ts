import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { CfsServiceSpecDetailComponent } from 'app/entities/cfs-service-spec/cfs-service-spec-detail.component';
import { CfsServiceSpec } from 'app/shared/model/cfs-service-spec.model';

describe('Component Tests', () => {
  describe('CfsServiceSpec Management Detail Component', () => {
    let comp: CfsServiceSpecDetailComponent;
    let fixture: ComponentFixture<CfsServiceSpecDetailComponent>;
    const route = ({ data: of({ cfsServiceSpec: new CfsServiceSpec('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [CfsServiceSpecDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CfsServiceSpecDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CfsServiceSpecDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load cfsServiceSpec on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.cfsServiceSpec).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
