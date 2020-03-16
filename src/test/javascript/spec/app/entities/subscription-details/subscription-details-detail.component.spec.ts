import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { SubscriptionDetailsDetailComponent } from 'app/entities/subscription-details/subscription-details-detail.component';
import { SubscriptionDetails } from 'app/shared/model/subscription-details.model';

describe('Component Tests', () => {
  describe('SubscriptionDetails Management Detail Component', () => {
    let comp: SubscriptionDetailsDetailComponent;
    let fixture: ComponentFixture<SubscriptionDetailsDetailComponent>;
    const route = ({ data: of({ subscriptionDetails: new SubscriptionDetails('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [SubscriptionDetailsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SubscriptionDetailsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SubscriptionDetailsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load subscriptionDetails on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.subscriptionDetails).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
