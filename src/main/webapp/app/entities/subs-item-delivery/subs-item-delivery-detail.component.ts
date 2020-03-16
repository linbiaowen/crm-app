import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISubsItemDelivery } from 'app/shared/model/subs-item-delivery.model';

@Component({
  selector: 'jhi-subs-item-delivery-detail',
  templateUrl: './subs-item-delivery-detail.component.html'
})
export class SubsItemDeliveryDetailComponent implements OnInit {
  subsItemDelivery: ISubsItemDelivery | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ subsItemDelivery }) => (this.subsItemDelivery = subsItemDelivery));
  }

  previousState(): void {
    window.history.back();
  }
}
