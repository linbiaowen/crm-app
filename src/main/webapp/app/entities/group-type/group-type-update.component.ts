import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IGroupType, GroupType } from 'app/shared/model/group-type.model';
import { GroupTypeService } from './group-type.service';

@Component({
  selector: 'jhi-group-type-update',
  templateUrl: './group-type-update.component.html'
})
export class GroupTypeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    groupTypeId: [null, [Validators.required]],
    groupType: [null, [Validators.required]],
    startDate: [],
    endDate: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]]
  });

  constructor(protected groupTypeService: GroupTypeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ groupType }) => {
      if (!groupType.id) {
        const today = moment().startOf('day');
        groupType.startDate = today;
        groupType.endDate = today;
        groupType.createdDate = today;
        groupType.lastUpdatedDate = today;
      }

      this.updateForm(groupType);
    });
  }

  updateForm(groupType: IGroupType): void {
    this.editForm.patchValue({
      id: groupType.id,
      groupTypeId: groupType.groupTypeId,
      groupType: groupType.groupType,
      startDate: groupType.startDate ? groupType.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: groupType.endDate ? groupType.endDate.format(DATE_TIME_FORMAT) : null,
      createdDate: groupType.createdDate ? groupType.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: groupType.createdBy,
      lastUpdatedDate: groupType.lastUpdatedDate ? groupType.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: groupType.lastUpdatedBy,
      tenantId: groupType.tenantId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const groupType = this.createFromForm();
    if (groupType.id !== undefined) {
      this.subscribeToSaveResponse(this.groupTypeService.update(groupType));
    } else {
      this.subscribeToSaveResponse(this.groupTypeService.create(groupType));
    }
  }

  private createFromForm(): IGroupType {
    return {
      ...new GroupType(),
      id: this.editForm.get(['id'])!.value,
      groupTypeId: this.editForm.get(['groupTypeId'])!.value,
      groupType: this.editForm.get(['groupType'])!.value,
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
      tenantId: this.editForm.get(['tenantId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGroupType>>): void {
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
}
