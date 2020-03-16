import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { CrmwebTestModule } from '../../../test.module';
import { SubsPurchaseControlComponent } from 'app/entities/subs-purchase-control/subs-purchase-control.component';
import { SubsPurchaseControlService } from 'app/entities/subs-purchase-control/subs-purchase-control.service';
import { SubsPurchaseControl } from 'app/shared/model/subs-purchase-control.model';

describe('Component Tests', () => {
  describe('SubsPurchaseControl Management Component', () => {
    let comp: SubsPurchaseControlComponent;
    let fixture: ComponentFixture<SubsPurchaseControlComponent>;
    let service: SubsPurchaseControlService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [SubsPurchaseControlComponent],
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
        .overrideTemplate(SubsPurchaseControlComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SubsPurchaseControlComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SubsPurchaseControlService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SubsPurchaseControl('123')],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.subsPurchaseControls && comp.subsPurchaseControls[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SubsPurchaseControl('123')],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.subsPurchaseControls && comp.subsPurchaseControls[0]).toEqual(jasmine.objectContaining({ id: '123' }));
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
