import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { DeliveryOptionDetailComponent } from 'app/entities/delivery-option/delivery-option-detail.component';
import { DeliveryOption } from 'app/shared/model/delivery-option.model';

describe('Component Tests', () => {
  describe('DeliveryOption Management Detail Component', () => {
    let comp: DeliveryOptionDetailComponent;
    let fixture: ComponentFixture<DeliveryOptionDetailComponent>;
    const route = ({ data: of({ deliveryOption: new DeliveryOption('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [DeliveryOptionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DeliveryOptionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DeliveryOptionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load deliveryOption on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.deliveryOption).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
