import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { CustSubscriptionDetailComponent } from 'app/entities/cust-subscription/cust-subscription-detail.component';
import { CustSubscription } from 'app/shared/model/cust-subscription.model';

describe('Component Tests', () => {
  describe('CustSubscription Management Detail Component', () => {
    let comp: CustSubscriptionDetailComponent;
    let fixture: ComponentFixture<CustSubscriptionDetailComponent>;
    const route = ({ data: of({ custSubscription: new CustSubscription('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [CustSubscriptionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CustSubscriptionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CustSubscriptionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load custSubscription on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.custSubscription).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
