import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { CfsServicesDetailComponent } from 'app/entities/cfs-services/cfs-services-detail.component';
import { CfsServices } from 'app/shared/model/cfs-services.model';

describe('Component Tests', () => {
  describe('CfsServices Management Detail Component', () => {
    let comp: CfsServicesDetailComponent;
    let fixture: ComponentFixture<CfsServicesDetailComponent>;
    const route = ({ data: of({ cfsServices: new CfsServices('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [CfsServicesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CfsServicesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CfsServicesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load cfsServices on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.cfsServices).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
