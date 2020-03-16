import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { DataServiceSpecDetailComponent } from 'app/entities/data-service-spec/data-service-spec-detail.component';
import { DataServiceSpec } from 'app/shared/model/data-service-spec.model';

describe('Component Tests', () => {
  describe('DataServiceSpec Management Detail Component', () => {
    let comp: DataServiceSpecDetailComponent;
    let fixture: ComponentFixture<DataServiceSpecDetailComponent>;
    const route = ({ data: of({ dataServiceSpec: new DataServiceSpec('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [DataServiceSpecDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DataServiceSpecDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DataServiceSpecDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load dataServiceSpec on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dataServiceSpec).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
