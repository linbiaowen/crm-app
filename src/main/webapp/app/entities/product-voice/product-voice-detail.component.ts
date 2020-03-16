import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProductVoice } from 'app/shared/model/product-voice.model';

@Component({
  selector: 'jhi-product-voice-detail',
  templateUrl: './product-voice-detail.component.html'
})
export class ProductVoiceDetailComponent implements OnInit {
  productVoice: IProductVoice | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productVoice }) => (this.productVoice = productVoice));
  }

  previousState(): void {
    window.history.back();
  }
}
