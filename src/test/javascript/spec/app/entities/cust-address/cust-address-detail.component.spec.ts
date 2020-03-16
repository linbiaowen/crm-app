import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { CustAddressDetailComponent } from 'app/entities/cust-address/cust-address-detail.component';
import { CustAddress } from 'app/shared/model/cust-address.model';

describe('Component Tests', () => {
  describe('CustAddress Management Detail Component', () => {
    let comp: CustAddressDetailComponent;
    let fixture: ComponentFixture<CustAddressDetailComponent>;
    const route = ({ data: of({ custAddress: new CustAddress('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [CustAddressDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CustAddressDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CustAddressDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load custAddress on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.custAddress).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
