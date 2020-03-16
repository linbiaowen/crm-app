import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IGroupEndReason, GroupEndReason } from 'app/shared/model/group-end-reason.model';
import { GroupEndReasonService } from './group-end-reason.service';

@Component({
  selector: 'jhi-group-end-reason-update',
  templateUrl: './group-end-reason-update.component.html'
})
export class GroupEndReasonUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    endReasonCode: [null, [Validators.required]],
    endReason: [null, [Validators.required]],
    startDate: [],
    endDate: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]]
  });

  constructor(protected groupEndReasonService: GroupEndReasonService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ groupEndReason }) => {
      if (!groupEndReason.id) {
        const today = moment().startOf('day');
        groupEndReason.startDate = today;
        groupEndReason.endDate = today;
        groupEndReason.createdDate = today;
        groupEndReason.lastUpdatedDate = today;
      }

      this.updateForm(groupEndReason);
    });
  }

  updateForm(groupEndReason: IGroupEndReason): void {
    this.editForm.patchValue({
      id: groupEndReason.id,
      endReasonCode: groupEndReason.endReasonCode,
      endReason: groupEndReason.endReason,
      startDate: groupEndReason.startDate ? groupEndReason.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: groupEndReason.endDate ? groupEndReason.endDate.format(DATE_TIME_FORMAT) : null,
      createdDate: groupEndReason.createdDate ? groupEndReason.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: groupEndReason.createdBy,
      lastUpdatedDate: groupEndReason.lastUpdatedDate ? groupEndReason.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: groupEndReason.lastUpdatedBy,
      tenantId: groupEndReason.tenantId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const groupEndReason = this.createFromForm();
    if (groupEndReason.id !== undefined) {
      this.subscribeToSaveResponse(this.groupEndReasonService.update(groupEndReason));
    } else {
      this.subscribeToSaveResponse(this.groupEndReasonService.create(groupEndReason));
    }
  }

  private createFromForm(): IGroupEndReason {
    return {
      ...new GroupEndReason(),
      id: this.editForm.get(['id'])!.value,
      endReasonCode: this.editForm.get(['endReasonCode'])!.value,
      endReason: this.editForm.get(['endReason'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGroupEndReason>>): void {
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
