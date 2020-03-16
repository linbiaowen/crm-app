import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { SupremeMasterDetailComponent } from 'app/entities/supreme-master/supreme-master-detail.component';
import { SupremeMaster } from 'app/shared/model/supreme-master.model';

describe('Component Tests', () => {
  describe('SupremeMaster Management Detail Component', () => {
    let comp: SupremeMasterDetailComponent;
    let fixture: ComponentFixture<SupremeMasterDetailComponent>;
    const route = ({ data: of({ supremeMaster: new SupremeMaster('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [SupremeMasterDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SupremeMasterDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SupremeMasterDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load supremeMaster on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.supremeMaster).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
