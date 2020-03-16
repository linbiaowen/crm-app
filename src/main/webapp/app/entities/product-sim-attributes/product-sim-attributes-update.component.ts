import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IProductSimAttributes, ProductSimAttributes } from 'app/shared/model/product-sim-attributes.model';
import { ProductSimAttributesService } from './product-sim-attributes.service';

@Component({
  selector: 'jhi-product-sim-attributes-update',
  templateUrl: './product-sim-attributes-update.component.html'
})
export class ProductSimAttributesUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    simType: [null, [Validators.required]],
    imsiRangeFrom: [],
    imsiRangeTo: [],
    simRepositoryRef: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]]
  });

  constructor(
    protected productSimAttributesService: ProductSimAttributesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productSimAttributes }) => {
      if (!productSimAttributes.id) {
        const today = moment().startOf('day');
        productSimAttributes.createdDate = today;
        productSimAttributes.lastUpdatedDate = today;
      }

      this.updateForm(productSimAttributes);
    });
  }

  updateForm(productSimAttributes: IProductSimAttributes): void {
    this.editForm.patchValue({
      id: productSimAttributes.id,
      simType: productSimAttributes.simType,
      imsiRangeFrom: productSimAttributes.imsiRangeFrom,
      imsiRangeTo: productSimAttributes.imsiRangeTo,
      simRepositoryRef: productSimAttributes.simRepositoryRef,
      createdDate: productSimAttributes.createdDate ? productSimAttributes.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: productSimAttributes.createdBy,
      lastUpdatedDate: productSimAttributes.lastUpdatedDate ? productSimAttributes.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: productSimAttributes.lastUpdatedBy,
      tenantId: productSimAttributes.tenantId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const productSimAttributes = this.createFromForm();
    if (productSimAttributes.id !== undefined) {
      this.subscribeToSaveResponse(this.productSimAttributesService.update(productSimAttributes));
    } else {
      this.subscribeToSaveResponse(this.productSimAttributesService.create(productSimAttributes));
    }
  }

  private createFromForm(): IProductSimAttributes {
    return {
      ...new ProductSimAttributes(),
      id: this.editForm.get(['id'])!.value,
      simType: this.editForm.get(['simType'])!.value,
      imsiRangeFrom: this.editForm.get(['imsiRangeFrom'])!.value,
      imsiRangeTo: this.editForm.get(['imsiRangeTo'])!.value,
      simRepositoryRef: this.editForm.get(['simRepositoryRef'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProductSimAttributes>>): void {
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
