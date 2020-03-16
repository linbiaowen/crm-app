import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { ProductVoiceDetailComponent } from 'app/entities/product-voice/product-voice-detail.component';
import { ProductVoice } from 'app/shared/model/product-voice.model';

describe('Component Tests', () => {
  describe('ProductVoice Management Detail Component', () => {
    let comp: ProductVoiceDetailComponent;
    let fixture: ComponentFixture<ProductVoiceDetailComponent>;
    const route = ({ data: of({ productVoice: new ProductVoice('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [ProductVoiceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProductVoiceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProductVoiceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load productVoice on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.productVoice).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
