import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { SubsDeliveryItemDetailsDetailComponent } from 'app/entities/subs-delivery-item-details/subs-delivery-item-details-detail.component';
import { SubsDeliveryItemDetails } from 'app/shared/model/subs-delivery-item-details.model';

describe('Component Tests', () => {
  describe('SubsDeliveryItemDetails Management Detail Component', () => {
    let comp: SubsDeliveryItemDetailsDetailComponent;
    let fixture: ComponentFixture<SubsDeliveryItemDetailsDetailComponent>;
    const route = ({ data: of({ subsDeliveryItemDetails: new SubsDeliveryItemDetails('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [SubsDeliveryItemDetailsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SubsDeliveryItemDetailsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SubsDeliveryItemDetailsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load subsDeliveryItemDetails on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.subsDeliveryItemDetails).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
