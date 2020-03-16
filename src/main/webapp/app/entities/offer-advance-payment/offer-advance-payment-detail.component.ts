import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOfferAdvancePayment } from 'app/shared/model/offer-advance-payment.model';

@Component({
  selector: 'jhi-offer-advance-payment-detail',
  templateUrl: './offer-advance-payment-detail.component.html'
})
export class OfferAdvancePaymentDetailComponent implements OnInit {
  offerAdvancePayment: IOfferAdvancePayment | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ offerAdvancePayment }) => (this.offerAdvancePayment = offerAdvancePayment));
  }

  previousState(): void {
    window.history.back();
  }
}
