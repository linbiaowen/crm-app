import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOfferDiscount } from 'app/shared/model/offer-discount.model';

@Component({
  selector: 'jhi-offer-discount-detail',
  templateUrl: './offer-discount-detail.component.html'
})
export class OfferDiscountDetailComponent implements OnInit {
  offerDiscount: IOfferDiscount | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ offerDiscount }) => (this.offerDiscount = offerDiscount));
  }

  previousState(): void {
    window.history.back();
  }
}
