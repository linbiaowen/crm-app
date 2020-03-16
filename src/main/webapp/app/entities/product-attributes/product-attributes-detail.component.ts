import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProductAttributes } from 'app/shared/model/product-attributes.model';

@Component({
  selector: 'jhi-product-attributes-detail',
  templateUrl: './product-attributes-detail.component.html'
})
export class ProductAttributesDetailComponent implements OnInit {
  productAttributes: IProductAttributes | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productAttributes }) => (this.productAttributes = productAttributes));
  }

  previousState(): void {
    window.history.back();
  }
}
