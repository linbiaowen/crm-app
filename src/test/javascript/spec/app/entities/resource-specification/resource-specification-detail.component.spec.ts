import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { ResourceSpecificationDetailComponent } from 'app/entities/resource-specification/resource-specification-detail.component';
import { ResourceSpecification } from 'app/shared/model/resource-specification.model';

describe('Component Tests', () => {
  describe('ResourceSpecification Management Detail Component', () => {
    let comp: ResourceSpecificationDetailComponent;
    let fixture: ComponentFixture<ResourceSpecificationDetailComponent>;
    const route = ({ data: of({ resourceSpecification: new ResourceSpecification('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [ResourceSpecificationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ResourceSpecificationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ResourceSpecificationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load resourceSpecification on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.resourceSpecification).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
