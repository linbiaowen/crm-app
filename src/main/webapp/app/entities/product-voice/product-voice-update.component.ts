import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IProductVoice, ProductVoice } from 'app/shared/model/product-voice.model';
import { ProductVoiceService } from './product-voice.service';

@Component({
  selector: 'jhi-product-voice-update',
  templateUrl: './product-voice-update.component.html'
})
export class ProductVoiceUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    voiceId: [],
    productSpecId: [null, [Validators.required]],
    unit: [null, [Validators.required]],
    volume: [null, [Validators.required]],
    roamingFlag: [],
    minTransferQuota: [],
    maxTransferQuota: [],
    minRetentionQuota: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]]
  });

  constructor(protected productVoiceService: ProductVoiceService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productVoice }) => {
      if (!productVoice.id) {
        const today = moment().startOf('day');
        productVoice.createdDate = today;
        productVoice.lastUpdatedDate = today;
      }

      this.updateForm(productVoice);
    });
  }

  updateForm(productVoice: IProductVoice): void {
    this.editForm.patchValue({
      id: productVoice.id,
      voiceId: productVoice.voiceId,
      productSpecId: productVoice.productSpecId,
      unit: productVoice.unit,
      volume: productVoice.volume,
      roamingFlag: productVoice.roamingFlag,
      minTransferQuota: productVoice.minTransferQuota,
      maxTransferQuota: productVoice.maxTransferQuota,
      minRetentionQuota: productVoice.minRetentionQuota,
      createdDate: productVoice.createdDate ? productVoice.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: productVoice.createdBy,
      lastUpdatedDate: productVoice.lastUpdatedDate ? productVoice.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: productVoice.lastUpdatedBy,
      tenantId: productVoice.tenantId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const productVoice = this.createFromForm();
    if (productVoice.id !== undefined) {
      this.subscribeToSaveResponse(this.productVoiceService.update(productVoice));
    } else {
      this.subscribeToSaveResponse(this.productVoiceService.create(productVoice));
    }
  }

  private createFromForm(): IProductVoice {
    return {
      ...new ProductVoice(),
      id: this.editForm.get(['id'])!.value,
      voiceId: this.editForm.get(['voiceId'])!.value,
      productSpecId: this.editForm.get(['productSpecId'])!.value,
      unit: this.editForm.get(['unit'])!.value,
      volume: this.editForm.get(['volume'])!.value,
      roamingFlag: this.editForm.get(['roamingFlag'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProductVoice>>): void {
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
