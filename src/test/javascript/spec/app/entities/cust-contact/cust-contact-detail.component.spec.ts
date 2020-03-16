import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { CustContactDetailComponent } from 'app/entities/cust-contact/cust-contact-detail.component';
import { CustContact } from 'app/shared/model/cust-contact.model';

describe('Component Tests', () => {
  describe('CustContact Management Detail Component', () => {
    let comp: CustContactDetailComponent;
    let fixture: ComponentFixture<CustContactDetailComponent>;
    const route = ({ data: of({ custContact: new CustContact('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [CustContactDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CustContactDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CustContactDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load custContact on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.custContact).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
