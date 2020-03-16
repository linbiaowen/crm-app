import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICfsServices } from 'app/shared/model/cfs-services.model';

@Component({
  selector: 'jhi-cfs-services-detail',
  templateUrl: './cfs-services-detail.component.html'
})
export class CfsServicesDetailComponent implements OnInit {
  cfsServices: ICfsServices | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cfsServices }) => (this.cfsServices = cfsServices));
  }

  previousState(): void {
    window.history.back();
  }
}
