import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICommMediaType, CommMediaType } from 'app/shared/model/comm-media-type.model';
import { CommMediaTypeService } from './comm-media-type.service';

@Component({
  selector: 'jhi-comm-media-type-update',
  templateUrl: './comm-media-type-update.component.html'
})
export class CommMediaTypeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    optoutMediaId: [null, [Validators.required]],
    mediaType: [null, [Validators.required]],
    mediaTypeDesc: [],
    startDate: [],
    endDate: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]]
  });

  constructor(protected commMediaTypeService: CommMediaTypeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ commMediaType }) => {
      if (!commMediaType.id) {
        const today = moment().startOf('day');
        commMediaType.startDate = today;
        commMediaType.endDate = today;
        commMediaType.createdDate = today;
        commMediaType.lastUpdatedDate = today;
      }

      this.updateForm(commMediaType);
    });
  }

  updateForm(commMediaType: ICommMediaType): void {
    this.editForm.patchValue({
      id: commMediaType.id,
      optoutMediaId: commMediaType.optoutMediaId,
      mediaType: commMediaType.mediaType,
      mediaTypeDesc: commMediaType.mediaTypeDesc,
      startDate: commMediaType.startDate ? commMediaType.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: commMediaType.endDate ? commMediaType.endDate.format(DATE_TIME_FORMAT) : null,
      createdDate: commMediaType.createdDate ? commMediaType.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: commMediaType.createdBy,
      lastUpdatedDate: commMediaType.lastUpdatedDate ? commMediaType.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: commMediaType.lastUpdatedBy,
      tenantId: commMediaType.tenantId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const commMediaType = this.createFromForm();
    if (commMediaType.id !== undefined) {
      this.subscribeToSaveResponse(this.commMediaTypeService.update(commMediaType));
    } else {
      this.subscribeToSaveResponse(this.commMediaTypeService.create(commMediaType));
    }
  }

  private createFromForm(): ICommMediaType {
    return {
      ...new CommMediaType(),
      id: this.editForm.get(['id'])!.value,
      optoutMediaId: this.editForm.get(['optoutMediaId'])!.value,
      mediaType: this.editForm.get(['mediaType'])!.value,
      mediaTypeDesc: this.editForm.get(['mediaTypeDesc'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICommMediaType>>): void {
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
