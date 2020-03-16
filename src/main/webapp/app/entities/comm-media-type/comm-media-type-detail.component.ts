import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICommMediaType } from 'app/shared/model/comm-media-type.model';

@Component({
  selector: 'jhi-comm-media-type-detail',
  templateUrl: './comm-media-type-detail.component.html'
})
export class CommMediaTypeDetailComponent implements OnInit {
  commMediaType: ICommMediaType | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ commMediaType }) => (this.commMediaType = commMediaType));
  }

  previousState(): void {
    window.history.back();
  }
}
