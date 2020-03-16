import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrderMaster } from 'app/shared/model/order-master.model';

@Component({
  selector: 'jhi-order-master-detail',
  templateUrl: './order-master-detail.component.html'
})
export class OrderMasterDetailComponent implements OnInit {
  orderMaster: IOrderMaster | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ orderMaster }) => (this.orderMaster = orderMaster));
  }

  previousState(): void {
    window.history.back();
  }
}
