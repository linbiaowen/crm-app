import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOfferProduct } from 'app/shared/model/offer-product.model';

@Component({
  selector: 'jhi-offer-product-detail',
  templateUrl: './offer-product-detail.component.html'
})
export class OfferProductDetailComponent implements OnInit {
  offerProduct: IOfferProduct | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ offerProduct }) => (this.offerProduct = offerProduct));
  }

  previousState(): void {
    window.history.back();
  }
}
