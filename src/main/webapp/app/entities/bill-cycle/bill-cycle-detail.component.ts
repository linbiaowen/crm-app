import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBillCycle } from 'app/shared/model/bill-cycle.model';

@Component({
  selector: 'jhi-bill-cycle-detail',
  templateUrl: './bill-cycle-detail.component.html'
})
export class BillCycleDetailComponent implements OnInit {
  billCycle: IBillCycle | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ billCycle }) => (this.billCycle = billCycle));
  }

  previousState(): void {
    window.history.back();
  }
}
