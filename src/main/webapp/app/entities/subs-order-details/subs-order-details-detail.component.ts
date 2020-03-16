import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISubsOrderDetails } from 'app/shared/model/subs-order-details.model';

@Component({
  selector: 'jhi-subs-order-details-detail',
  templateUrl: './subs-order-details-detail.component.html'
})
export class SubsOrderDetailsDetailComponent implements OnInit {
  subsOrderDetails: ISubsOrderDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ subsOrderDetails }) => (this.subsOrderDetails = subsOrderDetails));
  }

  previousState(): void {
    window.history.back();
  }
}
