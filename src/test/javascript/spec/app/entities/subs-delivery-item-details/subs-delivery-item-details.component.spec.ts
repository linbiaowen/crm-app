import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { CrmwebTestModule } from '../../../test.module';
import { SubsDeliveryItemDetailsComponent } from 'app/entities/subs-delivery-item-details/subs-delivery-item-details.component';
import { SubsDeliveryItemDetailsService } from 'app/entities/subs-delivery-item-details/subs-delivery-item-details.service';
import { SubsDeliveryItemDetails } from 'app/shared/model/subs-delivery-item-details.model';

describe('Component Tests', () => {
  describe('SubsDeliveryItemDetails Management Component', () => {
    let comp: SubsDeliveryItemDetailsComponent;
    let fixture: ComponentFixture<SubsDeliveryItemDetailsComponent>;
    let service: SubsDeliveryItemDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [SubsDeliveryItemDetailsComponent],
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
        .overrideTemplate(SubsDeliveryItemDetailsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SubsDeliveryItemDetailsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SubsDeliveryItemDetailsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SubsDeliveryItemDetails('123')],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.subsDeliveryItemDetails && comp.subsDeliveryItemDetails[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SubsDeliveryItemDetails('123')],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.subsDeliveryItemDetails && comp.subsDeliveryItemDetails[0]).toEqual(jasmine.objectContaining({ id: '123' }));
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
