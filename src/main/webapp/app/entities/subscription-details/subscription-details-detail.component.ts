import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISubscriptionDetails } from 'app/shared/model/subscription-details.model';

@Component({
  selector: 'jhi-subscription-details-detail',
  templateUrl: './subscription-details-detail.component.html'
})
export class SubscriptionDetailsDetailComponent implements OnInit {
  subscriptionDetails: ISubscriptionDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ subscriptionDetails }) => (this.subscriptionDetails = subscriptionDetails));
  }

  previousState(): void {
    window.history.back();
  }
}
