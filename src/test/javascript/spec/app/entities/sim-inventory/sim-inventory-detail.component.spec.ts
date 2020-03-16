import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrmwebTestModule } from '../../../test.module';
import { SimInventoryDetailComponent } from 'app/entities/sim-inventory/sim-inventory-detail.component';
import { SimInventory } from 'app/shared/model/sim-inventory.model';

describe('Component Tests', () => {
  describe('SimInventory Management Detail Component', () => {
    let comp: SimInventoryDetailComponent;
    let fixture: ComponentFixture<SimInventoryDetailComponent>;
    const route = ({ data: of({ simInventory: new SimInventory('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrmwebTestModule],
        declarations: [SimInventoryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SimInventoryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SimInventoryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load simInventory on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.simInventory).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
