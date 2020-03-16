import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IModelCategory } from 'app/shared/model/model-category.model';

@Component({
  selector: 'jhi-model-category-detail',
  templateUrl: './model-category-detail.component.html'
})
export class ModelCategoryDetailComponent implements OnInit {
  modelCategory: IModelCategory | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ modelCategory }) => (this.modelCategory = modelCategory));
  }

  previousState(): void {
    window.history.back();
  }
}
