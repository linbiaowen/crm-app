import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IProductBoxType, ProductBoxType } from 'app/shared/model/product-box-type.model';
import { ProductBoxTypeService } from './product-box-type.service';

@Component({
  selector: 'jhi-product-box-type-update',
  templateUrl: './product-box-type-update.component.html'
})
export class ProductBoxTypeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    boxType: [null, [Validators.required]],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]]
  });

  constructor(protected productBoxTypeService: ProductBoxTypeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productBoxType }) => {
      if (!productBoxType.id) {
        const today = moment().startOf('day');
        productBoxType.createdDate = today;
        productBoxType.lastUpdatedDate = today;
      }

      this.updateForm(productBoxType);
    });
  }

  updateForm(productBoxType: IProductBoxType): void {
    this.editForm.patchValue({
      id: productBoxType.id,
      boxType: productBoxType.boxType,
      createdDate: productBoxType.createdDate ? productBoxType.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: productBoxType.createdBy,
      lastUpdatedDate: productBoxType.lastUpdatedDate ? productBoxType.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: productBoxType.lastUpdatedBy,
      tenantId: productBoxType.tenantId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const productBoxType = this.createFromForm();
    if (productBoxType.id !== undefined) {
      this.subscribeToSaveResponse(this.productBoxTypeService.update(productBoxType));
    } else {
      this.subscribeToSaveResponse(this.productBoxTypeService.create(productBoxType));
    }
  }

  private createFromForm(): IProductBoxType {
    return {
      ...new ProductBoxType(),
      id: this.editForm.get(['id'])!.value,
      boxType: this.editForm.get(['boxType'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProductBoxType>>): void {
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
