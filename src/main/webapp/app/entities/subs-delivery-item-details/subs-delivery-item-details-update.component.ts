import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISubsDeliveryItemDetails, SubsDeliveryItemDetails } from 'app/shared/model/subs-delivery-item-details.model';
import { SubsDeliveryItemDetailsService } from './subs-delivery-item-details.service';
import { ISubsItemDelivery } from 'app/shared/model/subs-item-delivery.model';
import { SubsItemDeliveryService } from 'app/entities/subs-item-delivery/subs-item-delivery.service';

@Component({
  selector: 'jhi-subs-delivery-item-details-update',
  templateUrl: './subs-delivery-item-details-update.component.html'
})
export class SubsDeliveryItemDetailsUpdateComponent implements OnInit {
  isSaving = false;
  subsitemdeliveries: ISubsItemDelivery[] = [];

  editForm = this.fb.group({
    id: [],
    subscriptionItemId: [null, [Validators.required]],
    deliveryId: [null, [Validators.required]],
    productId: [null, [Validators.required]],
    productName: [],
    deviceType: [],
    deviceModel: [],
    deviceSerialNbr: [],
    imei: [],
    productTheme: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]],
    subsItemDelivery: []
  });

  constructor(
    protected subsDeliveryItemDetailsService: SubsDeliveryItemDetailsService,
    protected subsItemDeliveryService: SubsItemDeliveryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ subsDeliveryItemDetails }) => {
      if (!subsDeliveryItemDetails.id) {
        const today = moment().startOf('day');
        subsDeliveryItemDetails.createdDate = today;
        subsDeliveryItemDetails.lastUpdatedDate = today;
      }

      this.updateForm(subsDeliveryItemDetails);

      this.subsItemDeliveryService
        .query()
        .subscribe((res: HttpResponse<ISubsItemDelivery[]>) => (this.subsitemdeliveries = res.body || []));
    });
  }

  updateForm(subsDeliveryItemDetails: ISubsDeliveryItemDetails): void {
    this.editForm.patchValue({
      id: subsDeliveryItemDetails.id,
      subscriptionItemId: subsDeliveryItemDetails.subscriptionItemId,
      deliveryId: subsDeliveryItemDetails.deliveryId,
      productId: subsDeliveryItemDetails.productId,
      productName: subsDeliveryItemDetails.productName,
      deviceType: subsDeliveryItemDetails.deviceType,
      deviceModel: subsDeliveryItemDetails.deviceModel,
      deviceSerialNbr: subsDeliveryItemDetails.deviceSerialNbr,
      imei: subsDeliveryItemDetails.imei,
      productTheme: subsDeliveryItemDetails.productTheme,
      createdDate: subsDeliveryItemDetails.createdDate ? subsDeliveryItemDetails.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: subsDeliveryItemDetails.createdBy,
      lastUpdatedDate: subsDeliveryItemDetails.lastUpdatedDate ? subsDeliveryItemDetails.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: subsDeliveryItemDetails.lastUpdatedBy,
      tenantId: subsDeliveryItemDetails.tenantId,
      subsItemDelivery: subsDeliveryItemDetails.subsItemDelivery
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const subsDeliveryItemDetails = this.createFromForm();
    if (subsDeliveryItemDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.subsDeliveryItemDetailsService.update(subsDeliveryItemDetails));
    } else {
      this.subscribeToSaveResponse(this.subsDeliveryItemDetailsService.create(subsDeliveryItemDetails));
    }
  }

  private createFromForm(): ISubsDeliveryItemDetails {
    return {
      ...new SubsDeliveryItemDetails(),
      id: this.editForm.get(['id'])!.value,
      subscriptionItemId: this.editForm.get(['subscriptionItemId'])!.value,
      deliveryId: this.editForm.get(['deliveryId'])!.value,
      productId: this.editForm.get(['productId'])!.value,
      productName: this.editForm.get(['productName'])!.value,
      deviceType: this.editForm.get(['deviceType'])!.value,
      deviceModel: this.editForm.get(['deviceModel'])!.value,
      deviceSerialNbr: this.editForm.get(['deviceSerialNbr'])!.value,
      imei: this.editForm.get(['imei'])!.value,
      productTheme: this.editForm.get(['productTheme'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      lastUpdatedDate: this.editForm.get(['lastUpdatedDate'])!.value
        ? moment(this.editForm.get(['lastUpdatedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastUpdatedBy: this.editForm.get(['lastUpdatedBy'])!.value,
      tenantId: this.editForm.get(['tenantId'])!.value,
      subsItemDelivery: this.editForm.get(['subsItemDelivery'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISubsDeliveryItemDetails>>): void {
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

  trackById(index: number, item: ISubsItemDelivery): any {
    return item.id;
  }
}
