import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISimInventory } from 'app/shared/model/sim-inventory.model';

@Component({
  selector: 'jhi-sim-inventory-detail',
  templateUrl: './sim-inventory-detail.component.html'
})
export class SimInventoryDetailComponent implements OnInit {
  simInventory: ISimInventory | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ simInventory }) => (this.simInventory = simInventory));
  }

  previousState(): void {
    window.history.back();
  }
}
