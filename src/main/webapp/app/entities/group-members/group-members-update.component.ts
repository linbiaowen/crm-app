import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IGroupMembers, GroupMembers } from 'app/shared/model/group-members.model';
import { GroupMembersService } from './group-members.service';
import { ISubscriptionGroup } from 'app/shared/model/subscription-group.model';
import { SubscriptionGroupService } from 'app/entities/subscription-group/subscription-group.service';

@Component({
  selector: 'jhi-group-members-update',
  templateUrl: './group-members-update.component.html'
})
export class GroupMembersUpdateComponent implements OnInit {
  isSaving = false;
  subscriptiongroups: ISubscriptionGroup[] = [];

  editForm = this.fb.group({
    id: [],
    groupId: [null, [Validators.required]],
    subscriptionId: [null, [Validators.required]],
    msisdn: [null, [Validators.required]],
    groupRole: [null, [Validators.required]],
    endReasonCode: [],
    remarks: [],
    startDate: [],
    endDate: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]],
    subscriptionGroup: []
  });

  constructor(
    protected groupMembersService: GroupMembersService,
    protected subscriptionGroupService: SubscriptionGroupService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ groupMembers }) => {
      if (!groupMembers.id) {
        const today = moment().startOf('day');
        groupMembers.startDate = today;
        groupMembers.endDate = today;
        groupMembers.createdDate = today;
        groupMembers.lastUpdatedDate = today;
      }

      this.updateForm(groupMembers);

      this.subscriptionGroupService
        .query()
        .subscribe((res: HttpResponse<ISubscriptionGroup[]>) => (this.subscriptiongroups = res.body || []));
    });
  }

  updateForm(groupMembers: IGroupMembers): void {
    this.editForm.patchValue({
      id: groupMembers.id,
      groupId: groupMembers.groupId,
      subscriptionId: groupMembers.subscriptionId,
      msisdn: groupMembers.msisdn,
      groupRole: groupMembers.groupRole,
      endReasonCode: groupMembers.endReasonCode,
      remarks: groupMembers.remarks,
      startDate: groupMembers.startDate ? groupMembers.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: groupMembers.endDate ? groupMembers.endDate.format(DATE_TIME_FORMAT) : null,
      createdDate: groupMembers.createdDate ? groupMembers.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: groupMembers.createdBy,
      lastUpdatedDate: groupMembers.lastUpdatedDate ? groupMembers.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: groupMembers.lastUpdatedBy,
      tenantId: groupMembers.tenantId,
      subscriptionGroup: groupMembers.subscriptionGroup
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const groupMembers = this.createFromForm();
    if (groupMembers.id !== undefined) {
      this.subscribeToSaveResponse(this.groupMembersService.update(groupMembers));
    } else {
      this.subscribeToSaveResponse(this.groupMembersService.create(groupMembers));
    }
  }

  private createFromForm(): IGroupMembers {
    return {
      ...new GroupMembers(),
      id: this.editForm.get(['id'])!.value,
      groupId: this.editForm.get(['groupId'])!.value,
      subscriptionId: this.editForm.get(['subscriptionId'])!.value,
      msisdn: this.editForm.get(['msisdn'])!.value,
      groupRole: this.editForm.get(['groupRole'])!.value,
      endReasonCode: this.editForm.get(['endReasonCode'])!.value,
      remarks: this.editForm.get(['remarks'])!.value,
      startDate: this.editForm.get(['startDate'])!.value ? moment(this.editForm.get(['startDate'])!.value, DATE_TIME_FORMAT) : undefined,
      endDate: this.editForm.get(['endDate'])!.value ? moment(this.editForm.get(['endDate'])!.value, DATE_TIME_FORMAT) : undefined,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      lastUpdatedDate: this.editForm.get(['lastUpdatedDate'])!.value
        ? moment(this.editForm.get(['lastUpdatedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastUpdatedBy: this.editForm.get(['lastUpdatedBy'])!.value,
      tenantId: this.editForm.get(['tenantId'])!.value,
      subscriptionGroup: this.editForm.get(['subscriptionGroup'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGroupMembers>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: ISubscriptionGroup): any {
    return item.id;
  }
}
