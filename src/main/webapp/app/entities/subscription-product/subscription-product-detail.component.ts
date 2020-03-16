import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISubscriptionProduct } from 'app/shared/model/subscription-product.model';

@Component({
  selector: 'jhi-subscription-product-detail',
  templateUrl: './subscription-product-detail.component.html'
})
export class SubscriptionProductDetailComponent implements OnInit {
  subscriptionProduct: ISubscriptionProduct | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ subscriptionProduct }) => (this.subscriptionProduct = subscriptionProduct));
  }

  previousState(): void {
    window.history.back();
  }
}
