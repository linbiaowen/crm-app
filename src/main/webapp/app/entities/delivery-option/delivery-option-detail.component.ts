import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDeliveryOption } from 'app/shared/model/delivery-option.model';

@Component({
  selector: 'jhi-delivery-option-detail',
  templateUrl: './delivery-option-detail.component.html'
})
export class DeliveryOptionDetailComponent implements OnInit {
  deliveryOption: IDeliveryOption | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ deliveryOption }) => (this.deliveryOption = deliveryOption));
  }

  previousState(): void {
    window.history.back();
  }
}
