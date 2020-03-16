import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGroupType } from 'app/shared/model/group-type.model';

@Component({
  selector: 'jhi-group-type-detail',
  templateUrl: './group-type-detail.component.html'
})
export class GroupTypeDetailComponent implements OnInit {
  groupType: IGroupType | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ groupType }) => (this.groupType = groupType));
  }

  previousState(): void {
    window.history.back();
  }
}
