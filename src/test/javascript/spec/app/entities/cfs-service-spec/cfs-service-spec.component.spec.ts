import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { CrmwebTestModule } from '../../../test.module';
import { CfsServiceSpecComponent } from 'app/entities/cfs-service-spec/cfs-service-spec.component';
import { CfsServiceSpecService } from 'app/entities/cfs-service-spec/cfs-service-spec.service';
import { CfsServiceSpec } from 'app/shared/model/cfs-service-spec.model';

describe('Component Tests', () => {
  describe('CfsServiceSpec Management Component', () => {
    let comp: CfsServiceSpecComponent;
    let fixture: ComponentFixture<CfsServiceSpecComponent>;
    let service: CfsServiceSpecService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [CfsServiceSpecComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: {
                subscribe: (fn: (value: Data) => void) =>
                  fn({
                    pagingParams: {
                      predicate: 'id',
                      reverse: false,
                      page: 0
                    }
                  })
              }
            }
          }
        ]
      })
        .overrideTemplate(CfsServiceSpecComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CfsServiceSpecComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CfsServiceSpecService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CfsServiceSpec('123')],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.cfsServiceSpecs && comp.cfsServiceSpecs[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CfsServiceSpec('123')],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.cfsServiceSpecs && comp.cfsServiceSpecs[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
