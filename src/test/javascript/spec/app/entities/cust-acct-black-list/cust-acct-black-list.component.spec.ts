import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { CrmwebTestModule } from '../../../test.module';
import { CustAcctBlackListComponent } from 'app/entities/cust-acct-black-list/cust-acct-black-list.component';
import { CustAcctBlackListService } from 'app/entities/cust-acct-black-list/cust-acct-black-list.service';
import { CustAcctBlackList } from 'app/shared/model/cust-acct-black-list.model';

describe('Component Tests', () => {
  describe('CustAcctBlackList Management Component', () => {
    let comp: CustAcctBlackListComponent;
    let fixture: ComponentFixture<CustAcctBlackListComponent>;
    let service: CustAcctBlackListService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [CustAcctBlackListComponent],
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
        .overrideTemplate(CustAcctBlackListComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CustAcctBlackListComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CustAcctBlackListService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CustAcctBlackList('123')],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.custAcctBlackLists && comp.custAcctBlackLists[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CustAcctBlackList('123')],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.custAcctBlackLists && comp.custAcctBlackLists[0]).toEqual(jasmine.objectContaining({ id: '123' }));
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
