import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { CommMediaTypeDetailComponent } from 'app/entities/comm-media-type/comm-media-type-detail.component';
import { CommMediaType } from 'app/shared/model/comm-media-type.model';

describe('Component Tests', () => {
  describe('CommMediaType Management Detail Component', () => {
    let comp: CommMediaTypeDetailComponent;
    let fixture: ComponentFixture<CommMediaTypeDetailComponent>;
    const route = ({ data: of({ commMediaType: new CommMediaType('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [CommMediaTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CommMediaTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CommMediaTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load commMediaType on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.commMediaType).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
