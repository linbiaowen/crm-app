import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { SubscriptionProvisionDetailComponent } from 'app/entities/subscription-provision/subscription-provision-detail.component';
import { SubscriptionProvision } from 'app/shared/model/subscription-provision.model';

describe('Component Tests', () => {
  describe('SubscriptionProvision Management Detail Component', () => {
    let comp: SubscriptionProvisionDetailComponent;
    let fixture: ComponentFixture<SubscriptionProvisionDetailComponent>;
    const route = ({ data: of({ subscriptionProvision: new SubscriptionProvision('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [SubscriptionProvisionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SubscriptionProvisionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SubscriptionProvisionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load subscriptionProvision on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.subscriptionProvision).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
