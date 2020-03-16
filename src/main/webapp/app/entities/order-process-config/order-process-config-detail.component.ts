import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrderProcessConfig } from 'app/shared/model/order-process-config.model';

@Component({
  selector: 'jhi-order-process-config-detail',
  templateUrl: './order-process-config-detail.component.html'
})
export class OrderProcessConfigDetailComponent implements OnInit {
  orderProcessConfig: IOrderProcessConfig | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ orderProcessConfig }) => (this.orderProcessConfig = orderProcessConfig));
  }

  previousState(): void {
    window.history.back();
  }
}
