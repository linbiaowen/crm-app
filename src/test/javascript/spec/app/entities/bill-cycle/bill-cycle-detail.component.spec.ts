import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { BillCycleDetailComponent } from 'app/entities/bill-cycle/bill-cycle-detail.component';
import { BillCycle } from 'app/shared/model/bill-cycle.model';

describe('Component Tests', () => {
  describe('BillCycle Management Detail Component', () => {
    let comp: BillCycleDetailComponent;
    let fixture: ComponentFixture<BillCycleDetailComponent>;
    const route = ({ data: of({ billCycle: new BillCycle('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [BillCycleDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BillCycleDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BillCycleDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load billCycle on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.billCycle).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
