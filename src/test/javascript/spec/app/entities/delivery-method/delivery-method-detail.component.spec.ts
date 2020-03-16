import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { DeliveryMethodDetailComponent } from 'app/entities/delivery-method/delivery-method-detail.component';
import { DeliveryMethod } from 'app/shared/model/delivery-method.model';

describe('Component Tests', () => {
  describe('DeliveryMethod Management Detail Component', () => {
    let comp: DeliveryMethodDetailComponent;
    let fixture: ComponentFixture<DeliveryMethodDetailComponent>;
    const route = ({ data: of({ deliveryMethod: new DeliveryMethod('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [DeliveryMethodDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DeliveryMethodDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DeliveryMethodDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load deliveryMethod on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.deliveryMethod).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
