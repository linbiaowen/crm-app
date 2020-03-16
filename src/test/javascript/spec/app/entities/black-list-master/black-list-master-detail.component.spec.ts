import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { BlackListMasterDetailComponent } from 'app/entities/black-list-master/black-list-master-detail.component';
import { BlackListMaster } from 'app/shared/model/black-list-master.model';

describe('Component Tests', () => {
  describe('BlackListMaster Management Detail Component', () => {
    let comp: BlackListMasterDetailComponent;
    let fixture: ComponentFixture<BlackListMasterDetailComponent>;
    const route = ({ data: of({ blackListMaster: new BlackListMaster('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [BlackListMasterDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BlackListMasterDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BlackListMasterDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load blackListMaster on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.blackListMaster).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
