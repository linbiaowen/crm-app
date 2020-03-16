import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { GroupTypeDetailComponent } from 'app/entities/group-type/group-type-detail.component';
import { GroupType } from 'app/shared/model/group-type.model';

describe('Component Tests', () => {
  describe('GroupType Management Detail Component', () => {
    let comp: GroupTypeDetailComponent;
    let fixture: ComponentFixture<GroupTypeDetailComponent>;
    const route = ({ data: of({ groupType: new GroupType('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [GroupTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(GroupTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GroupTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load groupType on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.groupType).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
