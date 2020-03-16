import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICfsServiceSpec } from 'app/shared/model/cfs-service-spec.model';

@Component({
  selector: 'jhi-cfs-service-spec-detail',
  templateUrl: './cfs-service-spec-detail.component.html'
})
export class CfsServiceSpecDetailComponent implements OnInit {
  cfsServiceSpec: ICfsServiceSpec | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cfsServiceSpec }) => (this.cfsServiceSpec = cfsServiceSpec));
  }

  previousState(): void {
    window.history.back();
  }
}
