import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrderProcessStatusHistory } from 'app/shared/model/order-process-status-history.model';

@Component({
  selector: 'jhi-order-process-status-history-detail',
  templateUrl: './order-process-status-history-detail.component.html'
})
export class OrderProcessStatusHistoryDetailComponent implements OnInit {
  orderProcessStatusHistory: IOrderProcessStatusHistory | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ orderProcessStatusHistory }) => (this.orderProcessStatusHistory = orderProcessStatusHistory));
  }

  previousState(): void {
    window.history.back();
  }
}
