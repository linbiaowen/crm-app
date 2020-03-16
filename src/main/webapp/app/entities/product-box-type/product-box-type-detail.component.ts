import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProductBoxType } from 'app/shared/model/product-box-type.model';

@Component({
  selector: 'jhi-product-box-type-detail',
  templateUrl: './product-box-type-detail.component.html'
})
export class ProductBoxTypeDetailComponent implements OnInit {
  productBoxType: IProductBoxType | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productBoxType }) => (this.productBoxType = productBoxType));
  }

  previousState(): void {
    window.history.back();
  }
}
