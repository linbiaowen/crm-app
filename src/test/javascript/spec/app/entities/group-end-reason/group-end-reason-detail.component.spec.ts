import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { GroupEndReasonDetailComponent } from 'app/entities/group-end-reason/group-end-reason-detail.component';
import { GroupEndReason } from 'app/shared/model/group-end-reason.model';

describe('Component Tests', () => {
  describe('GroupEndReason Management Detail Component', () => {
    let comp: GroupEndReasonDetailComponent;
    let fixture: ComponentFixture<GroupEndReasonDetailComponent>;
    const route = ({ data: of({ groupEndReason: new GroupEndReason('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [GroupEndReasonDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(GroupEndReasonDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GroupEndReasonDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load groupEndReason on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.groupEndReason).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
