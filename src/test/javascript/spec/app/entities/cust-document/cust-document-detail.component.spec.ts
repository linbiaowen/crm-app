import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { CustDocumentDetailComponent } from 'app/entities/cust-document/cust-document-detail.component';
import { CustDocument } from 'app/shared/model/cust-document.model';

describe('Component Tests', () => {
  describe('CustDocument Management Detail Component', () => {
    let comp: CustDocumentDetailComponent;
    let fixture: ComponentFixture<CustDocumentDetailComponent>;
    const route = ({ data: of({ custDocument: new CustDocument('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [CustDocumentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CustDocumentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CustDocumentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load custDocument on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.custDocument).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
