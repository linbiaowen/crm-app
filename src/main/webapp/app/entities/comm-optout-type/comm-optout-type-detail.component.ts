import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICommOptoutType } from 'app/shared/model/comm-optout-type.model';

@Component({
  selector: 'jhi-comm-optout-type-detail',
  templateUrl: './comm-optout-type-detail.component.html'
})
export class CommOptoutTypeDetailComponent implements OnInit {
  commOptoutType: ICommOptoutType | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ commOptoutType }) => (this.commOptoutType = commOptoutType));
  }

  previousState(): void {
    window.history.back();
  }
}
