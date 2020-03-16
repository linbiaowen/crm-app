import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICustSubscription } from 'app/shared/model/cust-subscription.model';

@Component({
  selector: 'jhi-cust-subscription-detail',
  templateUrl: './cust-subscription-detail.component.html'
})
export class CustSubscriptionDetailComponent implements OnInit {
  custSubscription: ICustSubscription | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ custSubscription }) => (this.custSubscription = custSubscription));
  }

  previousState(): void {
    window.history.back();
  }
}
