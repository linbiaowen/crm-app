import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IModel, Model } from 'app/shared/model/model.model';
import { ModelService } from './model.service';

@Component({
  selector: 'jhi-model-update',
  templateUrl: './model-update.component.html'
})
export class ModelUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    modelCode: [null, [Validators.required]],
    modelGroup: [],
    status: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]]
  });

  constructor(protected modelService: ModelService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ model }) => {
      if (!model.id) {
        const today = moment().startOf('day');
        model.createdDate = today;
        model.lastUpdatedDate = today;
      }

      this.updateForm(model);
    });
  }

  updateForm(model: IModel): void {
    this.editForm.patchValue({
      id: model.id,
      modelCode: model.modelCode,
      modelGroup: model.modelGroup,
      status: model.status,
      createdDate: model.createdDate ? model.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: model.createdBy,
      lastUpdatedDate: model.lastUpdatedDate ? model.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: model.lastUpdatedBy,
      tenantId: model.tenantId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const model = this.createFromForm();
    if (model.id !== undefined) {
      this.subscribeToSaveResponse(this.modelService.update(model));
    } else {
      this.subscribeToSaveResponse(this.modelService.create(model));
    }
  }

  private createFromForm(): IModel {
    return {
      ...new Model(),
      id: this.editForm.get(['id'])!.value,
      modelCode: this.editForm.get(['modelCode'])!.value,
      modelGroup: this.editForm.get(['modelGroup'])!.value,
      status: this.editForm.get(['status'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IModel>>): void {
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
