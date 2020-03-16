import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDeliveryMethod } from 'app/shared/model/delivery-method.model';

@Component({
  selector: 'jhi-delivery-method-detail',
  templateUrl: './delivery-method-detail.component.html'
})
export class DeliveryMethodDetailComponent implements OnInit {
  deliveryMethod: IDeliveryMethod | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ deliveryMethod }) => (this.deliveryMethod = deliveryMethod));
  }

  previousState(): void {
    window.history.back();
  }
}
