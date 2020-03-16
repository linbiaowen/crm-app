import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { SubsItemDeliveryDetailComponent } from 'app/entities/subs-item-delivery/subs-item-delivery-detail.component';
import { SubsItemDelivery } from 'app/shared/model/subs-item-delivery.model';

describe('Component Tests', () => {
  describe('SubsItemDelivery Management Detail Component', () => {
    let comp: SubsItemDeliveryDetailComponent;
    let fixture: ComponentFixture<SubsItemDeliveryDetailComponent>;
    const route = ({ data: of({ subsItemDelivery: new SubsItemDelivery('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [SubsItemDeliveryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SubsItemDeliveryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SubsItemDeliveryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load subsItemDelivery on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.subsItemDelivery).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
