import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISubsPurchaseControl } from 'app/shared/model/subs-purchase-control.model';

@Component({
  selector: 'jhi-subs-purchase-control-detail',
  templateUrl: './subs-purchase-control-detail.component.html'
})
export class SubsPurchaseControlDetailComponent implements OnInit {
  subsPurchaseControl: ISubsPurchaseControl | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ subsPurchaseControl }) => (this.subsPurchaseControl = subsPurchaseControl));
  }

  previousState(): void {
    window.history.back();
  }
}
