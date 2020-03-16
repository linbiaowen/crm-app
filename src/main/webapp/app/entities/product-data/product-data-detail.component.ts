import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProductData } from 'app/shared/model/product-data.model';

@Component({
  selector: 'jhi-product-data-detail',
  templateUrl: './product-data-detail.component.html'
})
export class ProductDataDetailComponent implements OnInit {
  productData: IProductData | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productData }) => (this.productData = productData));
  }

  previousState(): void {
    window.history.back();
  }
}
