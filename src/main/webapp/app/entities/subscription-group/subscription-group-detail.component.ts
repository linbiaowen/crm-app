import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISubscriptionGroup } from 'app/shared/model/subscription-group.model';

@Component({
  selector: 'jhi-subscription-group-detail',
  templateUrl: './subscription-group-detail.component.html'
})
export class SubscriptionGroupDetailComponent implements OnInit {
  subscriptionGroup: ISubscriptionGroup | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ subscriptionGroup }) => (this.subscriptionGroup = subscriptionGroup));
  }

  previousState(): void {
    window.history.back();
  }
}
