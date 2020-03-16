import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IModelGroup } from 'app/shared/model/model-group.model';

@Component({
  selector: 'jhi-model-group-detail',
  templateUrl: './model-group-detail.component.html'
})
export class ModelGroupDetailComponent implements OnInit {
  modelGroup: IModelGroup | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ modelGroup }) => (this.modelGroup = modelGroup));
  }

  previousState(): void {
    window.history.back();
  }
}
