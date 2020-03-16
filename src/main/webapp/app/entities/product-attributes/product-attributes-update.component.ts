import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IProductAttributes, ProductAttributes } from 'app/shared/model/product-attributes.model';
import { ProductAttributesService } from './product-attributes.service';

@Component({
  selector: 'jhi-product-attributes-update',
  templateUrl: './product-attributes-update.component.html'
})
export class ProductAttributesUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    skuType: [],
    shippable: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]]
  });

  constructor(
    protected productAttributesService: ProductAttributesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productAttributes }) => {
      if (!productAttributes.id) {
        const today = moment().startOf('day');
        productAttributes.createdDate = today;
        productAttributes.lastUpdatedDate = today;
      }

      this.updateForm(productAttributes);
    });
  }

  updateForm(productAttributes: IProductAttributes): void {
    this.editForm.patchValue({
      id: productAttributes.id,
      skuType: productAttributes.skuType,
      shippable: productAttributes.shippable,
      createdDate: productAttributes.createdDate ? productAttributes.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: productAttributes.createdBy,
      lastUpdatedDate: productAttributes.lastUpdatedDate ? productAttributes.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: productAttributes.lastUpdatedBy,
      tenantId: productAttributes.tenantId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const productAttributes = this.createFromForm();
    if (productAttributes.id !== undefined) {
      this.subscribeToSaveResponse(this.productAttributesService.update(productAttributes));
    } else {
      this.subscribeToSaveResponse(this.productAttributesService.create(productAttributes));
    }
  }

  private createFromForm(): IProductAttributes {
    return {
      ...new ProductAttributes(),
      id: this.editForm.get(['id'])!.value,
      skuType: this.editForm.get(['skuType'])!.value,
      shippable: this.editForm.get(['shippable'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProductAttributes>>): void {
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
