import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { CrmwebTestModule } from '../../../test.module';
import { CustDocDataStoreDetailComponent } from 'app/entities/cust-doc-data-store/cust-doc-data-store-detail.component';
import { CustDocDataStore } from 'app/shared/model/cust-doc-data-store.model';

describe('Component Tests', () => {
  describe('CustDocDataStore Management Detail Component', () => {
    let comp: CustDocDataStoreDetailComponent;
    let fixture: ComponentFixture<CustDocDataStoreDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ custDocDataStore: new CustDocDataStore('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [CustDocDataStoreDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CustDocDataStoreDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CustDocDataStoreDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load custDocDataStore on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.custDocDataStore).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });

    describe('byteSize', () => {
      it('Should call byteSize from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'byteSize');
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.byteSize(fakeBase64);

        // THEN
        expect(dataUtils.byteSize).toBeCalledWith(fakeBase64);
      });
    });

    describe('openFile', () => {
      it('Should call openFile from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'openFile');
        const fakeContentType = 'fake content type';
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.openFile(fakeContentType, fakeBase64);

        // THEN
        expect(dataUtils.openFile).toBeCalledWith(fakeContentType, fakeBase64);
      });
    });
  });
});
