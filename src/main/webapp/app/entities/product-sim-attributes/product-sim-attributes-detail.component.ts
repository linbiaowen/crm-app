import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProductSimAttributes } from 'app/shared/model/product-sim-attributes.model';

@Component({
  selector: 'jhi-product-sim-attributes-detail',
  templateUrl: './product-sim-attributes-detail.component.html'
})
export class ProductSimAttributesDetailComponent implements OnInit {
  productSimAttributes: IProductSimAttributes | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productSimAttributes }) => (this.productSimAttributes = productSimAttributes));
  }

  previousState(): void {
    window.history.back();
  }
}
