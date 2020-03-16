import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IProductSms, ProductSms } from 'app/shared/model/product-sms.model';
import { ProductSmsService } from './product-sms.service';

@Component({
  selector: 'jhi-product-sms-update',
  templateUrl: './product-sms-update.component.html'
})
export class ProductSmsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    smsId: [],
    productSpecId: [null, [Validators.required]],
    unit: [null, [Validators.required]],
    volume: [null, [Validators.required]],
    smsType: [],
    roamingAllowed: [],
    minTransferQuota: [],
    maxTransferQuota: [],
    minRetentionQuota: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]]
  });

  constructor(protected productSmsService: ProductSmsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productSms }) => {
      if (!productSms.id) {
        const today = moment().startOf('day');
        productSms.createdDate = today;
        productSms.lastUpdatedDate = today;
      }

      this.updateForm(productSms);
    });
  }

  updateForm(productSms: IProductSms): void {
    this.editForm.patchValue({
      id: productSms.id,
      smsId: productSms.smsId,
      productSpecId: productSms.productSpecId,
      unit: productSms.unit,
      volume: productSms.volume,
      smsType: productSms.smsType,
      roamingAllowed: productSms.roamingAllowed,
      minTransferQuota: productSms.minTransferQuota,
      maxTransferQuota: productSms.maxTransferQuota,
      minRetentionQuota: productSms.minRetentionQuota,
      createdDate: productSms.createdDate ? productSms.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: productSms.createdBy,
      lastUpdatedDate: productSms.lastUpdatedDate ? productSms.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: productSms.lastUpdatedBy,
      tenantId: productSms.tenantId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const productSms = this.createFromForm();
    if (productSms.id !== undefined) {
      this.subscribeToSaveResponse(this.productSmsService.update(productSms));
    } else {
      this.subscribeToSaveResponse(this.productSmsService.create(productSms));
    }
  }

  private createFromForm(): IProductSms {
    return {
      ...new ProductSms(),
      id: this.editForm.get(['id'])!.value,
      smsId: this.editForm.get(['smsId'])!.value,
      productSpecId: this.editForm.get(['productSpecId'])!.value,
      unit: this.editForm.get(['unit'])!.value,
      volume: this.editForm.get(['volume'])!.value,
      smsType: this.editForm.get(['smsType'])!.value,
      roamingAllowed: this.editForm.get(['roamingAllowed'])!.value,
      minTransferQuota: this.editForm.get(['minTransferQuota'])!.value,
      maxTransferQuota: this.editForm.get(['maxTransferQuota'])!.value,
      minRetentionQuota: this.editForm.get(['minRetentionQuota'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProductSms>>): void {
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
