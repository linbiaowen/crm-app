import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IModelCategory, ModelCategory } from 'app/shared/model/model-category.model';
import { ModelCategoryService } from './model-category.service';

@Component({
  selector: 'jhi-model-category-update',
  templateUrl: './model-category-update.component.html'
})
export class ModelCategoryUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    modelCate: [],
    parentModelCate: [],
    modelCateDesc: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]]
  });

  constructor(protected modelCategoryService: ModelCategoryService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ modelCategory }) => {
      if (!modelCategory.id) {
        const today = moment().startOf('day');
        modelCategory.createdDate = today;
        modelCategory.lastUpdatedDate = today;
      }

      this.updateForm(modelCategory);
    });
  }

  updateForm(modelCategory: IModelCategory): void {
    this.editForm.patchValue({
      id: modelCategory.id,
      modelCate: modelCategory.modelCate,
      parentModelCate: modelCategory.parentModelCate,
      modelCateDesc: modelCategory.modelCateDesc,
      createdDate: modelCategory.createdDate ? modelCategory.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: modelCategory.createdBy,
      lastUpdatedDate: modelCategory.lastUpdatedDate ? modelCategory.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: modelCategory.lastUpdatedBy,
      tenantId: modelCategory.tenantId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const modelCategory = this.createFromForm();
    if (modelCategory.id !== undefined) {
      this.subscribeToSaveResponse(this.modelCategoryService.update(modelCategory));
    } else {
      this.subscribeToSaveResponse(this.modelCategoryService.create(modelCategory));
    }
  }

  private createFromForm(): IModelCategory {
    return {
      ...new ModelCategory(),
      id: this.editForm.get(['id'])!.value,
      modelCate: this.editForm.get(['modelCate'])!.value,
      parentModelCate: this.editForm.get(['parentModelCate'])!.value,
      modelCateDesc: this.editForm.get(['modelCateDesc'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IModelCategory>>): void {
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
