import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { ModelCategoryDetailComponent } from 'app/entities/model-category/model-category-detail.component';
import { ModelCategory } from 'app/shared/model/model-category.model';

describe('Component Tests', () => {
  describe('ModelCategory Management Detail Component', () => {
    let comp: ModelCategoryDetailComponent;
    let fixture: ComponentFixture<ModelCategoryDetailComponent>;
    const route = ({ data: of({ modelCategory: new ModelCategory('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [ModelCategoryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ModelCategoryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ModelCategoryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load modelCategory on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.modelCategory).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
