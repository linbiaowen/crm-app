import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IResourceSpecification } from 'app/shared/model/resource-specification.model';

@Component({
  selector: 'jhi-resource-specification-detail',
  templateUrl: './resource-specification-detail.component.html'
})
export class ResourceSpecificationDetailComponent implements OnInit {
  resourceSpecification: IResourceSpecification | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ resourceSpecification }) => (this.resourceSpecification = resourceSpecification));
  }

  previousState(): void {
    window.history.back();
  }
}
