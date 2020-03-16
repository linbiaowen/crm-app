import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGroupEndReason } from 'app/shared/model/group-end-reason.model';

@Component({
  selector: 'jhi-group-end-reason-detail',
  templateUrl: './group-end-reason-detail.component.html'
})
export class GroupEndReasonDetailComponent implements OnInit {
  groupEndReason: IGroupEndReason | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ groupEndReason }) => (this.groupEndReason = groupEndReason));
  }

  previousState(): void {
    window.history.back();
  }
}
