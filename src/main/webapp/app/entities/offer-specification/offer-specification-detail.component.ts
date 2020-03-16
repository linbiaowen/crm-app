import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOfferSpecification } from 'app/shared/model/offer-specification.model';

@Component({
  selector: 'jhi-offer-specification-detail',
  templateUrl: './offer-specification-detail.component.html'
})
export class OfferSpecificationDetailComponent implements OnInit {
  offerSpecification: IOfferSpecification | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ offerSpecification }) => (this.offerSpecification = offerSpecification));
  }

  previousState(): void {
    window.history.back();
  }
}
