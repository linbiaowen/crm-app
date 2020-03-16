import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDataServiceSpec } from 'app/shared/model/data-service-spec.model';

@Component({
  selector: 'jhi-data-service-spec-detail',
  templateUrl: './data-service-spec-detail.component.html'
})
export class DataServiceSpecDetailComponent implements OnInit {
  dataServiceSpec: IDataServiceSpec | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dataServiceSpec }) => (this.dataServiceSpec = dataServiceSpec));
  }

  previousState(): void {
    window.history.back();
  }
}
