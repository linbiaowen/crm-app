import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISubscriptionProvision } from 'app/shared/model/subscription-provision.model';

@Component({
  selector: 'jhi-subscription-provision-detail',
  templateUrl: './subscription-provision-detail.component.html'
})
export class SubscriptionProvisionDetailComponent implements OnInit {
  subscriptionProvision: ISubscriptionProvision | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ subscriptionProvision }) => (this.subscriptionProvision = subscriptionProvision));
  }

  previousState(): void {
    window.history.back();
  }
}
