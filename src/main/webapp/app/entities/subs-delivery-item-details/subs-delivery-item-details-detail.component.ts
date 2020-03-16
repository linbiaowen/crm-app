import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISubsDeliveryItemDetails } from 'app/shared/model/subs-delivery-item-details.model';

@Component({
  selector: 'jhi-subs-delivery-item-details-detail',
  templateUrl: './subs-delivery-item-details-detail.component.html'
})
export class SubsDeliveryItemDetailsDetailComponent implements OnInit {
  subsDeliveryItemDetails: ISubsDeliveryItemDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ subsDeliveryItemDetails }) => (this.subsDeliveryItemDetails = subsDeliveryItemDetails));
  }

  previousState(): void {
    window.history.back();
  }
}
