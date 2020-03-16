import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { CommOptoutTypeDetailComponent } from 'app/entities/comm-optout-type/comm-optout-type-detail.component';
import { CommOptoutType } from 'app/shared/model/comm-optout-type.model';

describe('Component Tests', () => {
  describe('CommOptoutType Management Detail Component', () => {
    let comp: CommOptoutTypeDetailComponent;
    let fixture: ComponentFixture<CommOptoutTypeDetailComponent>;
    const route = ({ data: of({ commOptoutType: new CommOptoutType('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [CommOptoutTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CommOptoutTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CommOptoutTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load commOptoutType on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.commOptoutType).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
