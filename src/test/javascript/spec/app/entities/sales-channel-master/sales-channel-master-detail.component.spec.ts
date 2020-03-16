import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { SalesChannelMasterDetailComponent } from 'app/entities/sales-channel-master/sales-channel-master-detail.component';
import { SalesChannelMaster } from 'app/shared/model/sales-channel-master.model';

describe('Component Tests', () => {
  describe('SalesChannelMaster Management Detail Component', () => {
    let comp: SalesChannelMasterDetailComponent;
    let fixture: ComponentFixture<SalesChannelMasterDetailComponent>;
    const route = ({ data: of({ salesChannelMaster: new SalesChannelMaster('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [SalesChannelMasterDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SalesChannelMasterDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SalesChannelMasterDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load salesChannelMaster on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.salesChannelMaster).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
