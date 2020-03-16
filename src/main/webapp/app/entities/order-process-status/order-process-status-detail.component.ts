import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrderProcessStatus } from 'app/shared/model/order-process-status.model';

@Component({
  selector: 'jhi-order-process-status-detail',
  templateUrl: './order-process-status-detail.component.html'
})
export class OrderProcessStatusDetailComponent implements OnInit {
  orderProcessStatus: IOrderProcessStatus | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ orderProcessStatus }) => (this.orderProcessStatus = orderProcessStatus));
  }

  previousState(): void {
    window.history.back();
  }
}
