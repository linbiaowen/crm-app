import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { SubscriptionGroupDetailComponent } from 'app/entities/subscription-group/subscription-group-detail.component';
import { SubscriptionGroup } from 'app/shared/model/subscription-group.model';

describe('Component Tests', () => {
  describe('SubscriptionGroup Management Detail Component', () => {
    let comp: SubscriptionGroupDetailComponent;
    let fixture: ComponentFixture<SubscriptionGroupDetailComponent>;
    const route = ({ data: of({ subscriptionGroup: new SubscriptionGroup('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [SubscriptionGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SubscriptionGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SubscriptionGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load subscriptionGroup on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.subscriptionGroup).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
