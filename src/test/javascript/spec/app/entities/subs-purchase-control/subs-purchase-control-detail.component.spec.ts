import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { SubsPurchaseControlDetailComponent } from 'app/entities/subs-purchase-control/subs-purchase-control-detail.component';
import { SubsPurchaseControl } from 'app/shared/model/subs-purchase-control.model';

describe('Component Tests', () => {
  describe('SubsPurchaseControl Management Detail Component', () => {
    let comp: SubsPurchaseControlDetailComponent;
    let fixture: ComponentFixture<SubsPurchaseControlDetailComponent>;
    const route = ({ data: of({ subsPurchaseControl: new SubsPurchaseControl('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [SubsPurchaseControlDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SubsPurchaseControlDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SubsPurchaseControlDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load subsPurchaseControl on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.subsPurchaseControl).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
