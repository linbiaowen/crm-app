import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISupremeMaster } from 'app/shared/model/supreme-master.model';

@Component({
  selector: 'jhi-supreme-master-detail',
  templateUrl: './supreme-master-detail.component.html'
})
export class SupremeMasterDetailComponent implements OnInit {
  supremeMaster: ISupremeMaster | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ supremeMaster }) => (this.supremeMaster = supremeMaster));
  }

  previousState(): void {
    window.history.back();
  }
}
