import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProductSms } from 'app/shared/model/product-sms.model';

@Component({
  selector: 'jhi-product-sms-detail',
  templateUrl: './product-sms-detail.component.html'
})
export class ProductSmsDetailComponent implements OnInit {
  productSms: IProductSms | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productSms }) => (this.productSms = productSms));
  }

  previousState(): void {
    window.history.back();
  }
}
