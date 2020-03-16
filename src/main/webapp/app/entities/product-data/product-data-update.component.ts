import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IProductData, ProductData } from 'app/shared/model/product-data.model';
import { ProductDataService } from './product-data.service';

@Component({
  selector: 'jhi-product-data-update',
  templateUrl: './product-data-update.component.html'
})
export class ProductDataUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    dataId: [],
    productSpecId: [null, [Validators.required]],
    unit: [null, [Validators.required]],
    volume: [null, [Validators.required]],
    dataSlab: [],
    dataSpeedCategory: [],
    specicalPackType: [],
    dataServiceType: [],
    roamingRegions: [],
    roamingCountries: [],
    roamingDayPassType: [],
    roamingPackValidPeriodType: [],
    roamingPackPeriod: [],
    roamingPackTerm: [],
    minTransferQuota: [],
    maxTransferQuota: [],
    minRetentionQuota: [],
    minTpTransferQuota: [],
    maxTpTransferQuota: [],
    minTpRetentionQuota: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]]
  });

  constructor(protected productDataService: ProductDataService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productData }) => {
      if (!productData.id) {
        const today = moment().startOf('day');
        productData.createdDate = today;
        productData.lastUpdatedDate = today;
      }

      this.updateForm(productData);
    });
  }

  updateForm(productData: IProductData): void {
    this.editForm.patchValue({
      id: productData.id,
      dataId: productData.dataId,
      productSpecId: productData.productSpecId,
      unit: productData.unit,
      volume: productData.volume,
      dataSlab: productData.dataSlab,
      dataSpeedCategory: productData.dataSpeedCategory,
      specicalPackType: productData.specicalPackType,
      dataServiceType: productData.dataServiceType,
      roamingRegions: productData.roamingRegions,
      roamingCountries: productData.roamingCountries,
      roamingDayPassType: productData.roamingDayPassType,
      roamingPackValidPeriodType: productData.roamingPackValidPeriodType,
      roamingPackPeriod: productData.roamingPackPeriod,
      roamingPackTerm: productData.roamingPackTerm,
      minTransferQuota: productData.minTransferQuota,
      maxTransferQuota: productData.maxTransferQuota,
      minRetentionQuota: productData.minRetentionQuota,
      minTpTransferQuota: productData.minTpTransferQuota,
      maxTpTransferQuota: productData.maxTpTransferQuota,
      minTpRetentionQuota: productData.minTpRetentionQuota,
      createdDate: productData.createdDate ? productData.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: productData.createdBy,
      lastUpdatedDate: productData.lastUpdatedDate ? productData.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: productData.lastUpdatedBy,
      tenantId: productData.tenantId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const productData = this.createFromForm();
    if (productData.id !== undefined) {
      this.subscribeToSaveResponse(this.productDataService.update(productData));
    } else {
      this.subscribeToSaveResponse(this.productDataService.create(productData));
    }
  }

  private createFromForm(): IProductData {
    return {
      ...new ProductData(),
      id: this.editForm.get(['id'])!.value,
      dataId: this.editForm.get(['dataId'])!.value,
      productSpecId: this.editForm.get(['productSpecId'])!.value,
      unit: this.editForm.get(['unit'])!.value,
      volume: this.editForm.get(['volume'])!.value,
      dataSlab: this.editForm.get(['dataSlab'])!.value,
      dataSpeedCategory: this.editForm.get(['dataSpeedCategory'])!.value,
      specicalPackType: this.editForm.get(['specicalPackType'])!.value,
      dataServiceType: this.editForm.get(['dataServiceType'])!.value,
      roamingRegions: this.editForm.get(['roamingRegions'])!.value,
      roamingCountries: this.editForm.get(['roamingCountries'])!.value,
      roamingDayPassType: this.editForm.get(['roamingDayPassType'])!.value,
      roamingPackValidPeriodType: this.editForm.get(['roamingPackValidPeriodType'])!.value,
      roamingPackPeriod: this.editForm.get(['roamingPackPeriod'])!.value,
      roamingPackTerm: this.editForm.get(['roamingPackTerm'])!.value,
      minTransferQuota: this.editForm.get(['minTransferQuota'])!.value,
      maxTransferQuota: this.editForm.get(['maxTransferQuota'])!.value,
      minRetentionQuota: this.editForm.get(['minRetentionQuota'])!.value,
      minTpTransferQuota: this.editForm.get(['minTpTransferQuota'])!.value,
      maxTpTransferQuota: this.editForm.get(['maxTpTransferQuota'])!.value,
      minTpRetentionQuota: this.editForm.get(['minTpRetentionQuota'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProductData>>): void {
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
