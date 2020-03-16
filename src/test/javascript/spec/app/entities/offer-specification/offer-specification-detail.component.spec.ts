import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { OfferSpecificationDetailComponent } from 'app/entities/offer-specification/offer-specification-detail.component';
import { OfferSpecification } from 'app/shared/model/offer-specification.model';

describe('Component Tests', () => {
  describe('OfferSpecification Management Detail Component', () => {
    let comp: OfferSpecificationDetailComponent;
    let fixture: ComponentFixture<OfferSpecificationDetailComponent>;
    const route = ({ data: of({ offerSpecification: new OfferSpecification('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [OfferSpecificationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(OfferSpecificationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OfferSpecificationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load offerSpecification on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.offerSpecification).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
