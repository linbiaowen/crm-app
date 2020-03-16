import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICommOptoutType, CommOptoutType } from 'app/shared/model/comm-optout-type.model';
import { CommOptoutTypeService } from './comm-optout-type.service';

@Component({
  selector: 'jhi-comm-optout-type-update',
  templateUrl: './comm-optout-type-update.component.html'
})
export class CommOptoutTypeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    optoutTypeId: [null, [Validators.required]],
    optoutType: [null, [Validators.required]],
    optoutTypeDesc: [],
    startDate: [],
    endDate: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]]
  });

  constructor(protected commOptoutTypeService: CommOptoutTypeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ commOptoutType }) => {
      if (!commOptoutType.id) {
        const today = moment().startOf('day');
        commOptoutType.startDate = today;
        commOptoutType.endDate = today;
        commOptoutType.createdDate = today;
        commOptoutType.lastUpdatedDate = today;
      }

      this.updateForm(commOptoutType);
    });
  }

  updateForm(commOptoutType: ICommOptoutType): void {
    this.editForm.patchValue({
      id: commOptoutType.id,
      optoutTypeId: commOptoutType.optoutTypeId,
      optoutType: commOptoutType.optoutType,
      optoutTypeDesc: commOptoutType.optoutTypeDesc,
      startDate: commOptoutType.startDate ? commOptoutType.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: commOptoutType.endDate ? commOptoutType.endDate.format(DATE_TIME_FORMAT) : null,
      createdDate: commOptoutType.createdDate ? commOptoutType.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: commOptoutType.createdBy,
      lastUpdatedDate: commOptoutType.lastUpdatedDate ? commOptoutType.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: commOptoutType.lastUpdatedBy,
      tenantId: commOptoutType.tenantId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const commOptoutType = this.createFromForm();
    if (commOptoutType.id !== undefined) {
      this.subscribeToSaveResponse(this.commOptoutTypeService.update(commOptoutType));
    } else {
      this.subscribeToSaveResponse(this.commOptoutTypeService.create(commOptoutType));
    }
  }

  private createFromForm(): ICommOptoutType {
    return {
      ...new CommOptoutType(),
      id: this.editForm.get(['id'])!.value,
      optoutTypeId: this.editForm.get(['optoutTypeId'])!.value,
      optoutType: this.editForm.get(['optoutType'])!.value,
      optoutTypeDesc: this.editForm.get(['optoutTypeDesc'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICommOptoutType>>): void {
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
