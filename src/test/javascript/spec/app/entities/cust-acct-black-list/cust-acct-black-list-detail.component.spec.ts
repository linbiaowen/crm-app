import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { CustAcctBlackListDetailComponent } from 'app/entities/cust-acct-black-list/cust-acct-black-list-detail.component';
import { CustAcctBlackList } from 'app/shared/model/cust-acct-black-list.model';

describe('Component Tests', () => {
  describe('CustAcctBlackList Management Detail Component', () => {
    let comp: CustAcctBlackListDetailComponent;
    let fixture: ComponentFixture<CustAcctBlackListDetailComponent>;
    const route = ({ data: of({ custAcctBlackList: new CustAcctBlackList('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [CustAcctBlackListDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CustAcctBlackListDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CustAcctBlackListDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load custAcctBlackList on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.custAcctBlackList).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
