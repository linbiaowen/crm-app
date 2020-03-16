import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { ModelGroupDetailComponent } from 'app/entities/model-group/model-group-detail.component';
import { ModelGroup } from 'app/shared/model/model-group.model';

describe('Component Tests', () => {
  describe('ModelGroup Management Detail Component', () => {
    let comp: ModelGroupDetailComponent;
    let fixture: ComponentFixture<ModelGroupDetailComponent>;
    const route = ({ data: of({ modelGroup: new ModelGroup('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [ModelGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ModelGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ModelGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load modelGroup on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.modelGroup).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
