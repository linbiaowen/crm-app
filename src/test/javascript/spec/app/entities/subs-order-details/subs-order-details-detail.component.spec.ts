import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { SubsOrderDetailsDetailComponent } from 'app/entities/subs-order-details/subs-order-details-detail.component';
import { SubsOrderDetails } from 'app/shared/model/subs-order-details.model';

describe('Component Tests', () => {
  describe('SubsOrderDetails Management Detail Component', () => {
    let comp: SubsOrderDetailsDetailComponent;
    let fixture: ComponentFixture<SubsOrderDetailsDetailComponent>;
    const route = ({ data: of({ subsOrderDetails: new SubsOrderDetails('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [SubsOrderDetailsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SubsOrderDetailsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SubsOrderDetailsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load subsOrderDetails on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.subsOrderDetails).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
