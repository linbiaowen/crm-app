import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGroupMembers } from 'app/shared/model/group-members.model';

@Component({
  selector: 'jhi-group-members-detail',
  templateUrl: './group-members-detail.component.html'
})
export class GroupMembersDetailComponent implements OnInit {
  groupMembers: IGroupMembers | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ groupMembers }) => (this.groupMembers = groupMembers));
  }

  previousState(): void {
    window.history.back();
  }
}
