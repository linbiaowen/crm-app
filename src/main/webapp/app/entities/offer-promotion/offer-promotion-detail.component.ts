import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOfferPromotion } from 'app/shared/model/offer-promotion.model';

@Component({
  selector: 'jhi-offer-promotion-detail',
  templateUrl: './offer-promotion-detail.component.html'
})
export class OfferPromotionDetailComponent implements OnInit {
  offerPromotion: IOfferPromotion | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ offerPromotion }) => (this.offerPromotion = offerPromotion));
  }

  previousState(): void {
    window.history.back();
  }
}
