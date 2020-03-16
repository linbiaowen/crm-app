import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { CustCommOptoutMasterDetailComponent } from 'app/entities/cust-comm-optout-master/cust-comm-optout-master-detail.component';
import { CustCommOptoutMaster } from 'app/shared/model/cust-comm-optout-master.model';

describe('Component Tests', () => {
  describe('CustCommOptoutMaster Management Detail Component', () => {
    let comp: CustCommOptoutMasterDetailComponent;
    let fixture: ComponentFixture<CustCommOptoutMasterDetailComponent>;
    const route = ({ data: of({ custCommOptoutMaster: new CustCommOptoutMaster('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [CustCommOptoutMasterDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CustCommOptoutMasterDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CustCommOptoutMasterDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load custCommOptoutMaster on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.custCommOptoutMaster).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
