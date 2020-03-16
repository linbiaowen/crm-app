import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISubsItemDelivery, SubsItemDelivery } from 'app/shared/model/subs-item-delivery.model';
import { SubsItemDeliveryService } from './subs-item-delivery.service';
import { IOrderMaster } from 'app/shared/model/order-master.model';
import { OrderMasterService } from 'app/entities/order-master/order-master.service';

@Component({
  selector: 'jhi-subs-item-delivery-update',
  templateUrl: './subs-item-delivery-update.component.html'
})
export class SubsItemDeliveryUpdateComponent implements OnInit {
  isSaving = false;
  ordermasters: IOrderMaster[] = [];

  editForm = this.fb.group({
    id: [],
    deliveryId: [null, [Validators.required]],
    orderId: [null, [Validators.required]],
    subscriptionId: [null, [Validators.required]],
    subscriptionItemId: [null, [Validators.required]],
    deliveryStatus: [null, [Validators.required]],
    deliveryMethodId: [null, [Validators.required]],
    deliveryRefCode: [],
    fileGenerationDate: [],
    fileReceivedDate: [],
    deliveryDate: [],
    remarks: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]],
    orderMaster: []
  });

  constructor(
    protected subsItemDeliveryService: SubsItemDeliveryService,
    protected orderMasterService: OrderMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ subsItemDelivery }) => {
      if (!subsItemDelivery.id) {
        const today = moment().startOf('day');
        subsItemDelivery.fileGenerationDate = today;
        subsItemDelivery.fileReceivedDate = today;
        subsItemDelivery.deliveryDate = today;
        subsItemDelivery.createdDate = today;
        subsItemDelivery.lastUpdatedDate = today;
      }

      this.updateForm(subsItemDelivery);

      this.orderMasterService.query().subscribe((res: HttpResponse<IOrderMaster[]>) => (this.ordermasters = res.body || []));
    });
  }

  updateForm(subsItemDelivery: ISubsItemDelivery): void {
    this.editForm.patchValue({
      id: subsItemDelivery.id,
      deliveryId: subsItemDelivery.deliveryId,
      orderId: subsItemDelivery.orderId,
      subscriptionId: subsItemDelivery.subscriptionId,
      subscriptionItemId: subsItemDelivery.subscriptionItemId,
      deliveryStatus: subsItemDelivery.deliveryStatus,
      deliveryMethodId: subsItemDelivery.deliveryMethodId,
      deliveryRefCode: subsItemDelivery.deliveryRefCode,
      fileGenerationDate: subsItemDelivery.fileGenerationDate ? subsItemDelivery.fileGenerationDate.format(DATE_TIME_FORMAT) : null,
      fileReceivedDate: subsItemDelivery.fileReceivedDate ? subsItemDelivery.fileReceivedDate.format(DATE_TIME_FORMAT) : null,
      deliveryDate: subsItemDelivery.deliveryDate ? subsItemDelivery.deliveryDate.format(DATE_TIME_FORMAT) : null,
      remarks: subsItemDelivery.remarks,
      createdDate: subsItemDelivery.createdDate ? subsItemDelivery.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: subsItemDelivery.createdBy,
      lastUpdatedDate: subsItemDelivery.lastUpdatedDate ? subsItemDelivery.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: subsItemDelivery.lastUpdatedBy,
      tenantId: subsItemDelivery.tenantId,
      orderMaster: subsItemDelivery.orderMaster
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const subsItemDelivery = this.createFromForm();
    if (subsItemDelivery.id !== undefined) {
      this.subscribeToSaveResponse(this.subsItemDeliveryService.update(subsItemDelivery));
    } else {
      this.subscribeToSaveResponse(this.subsItemDeliveryService.create(subsItemDelivery));
    }
  }

  private createFromForm(): ISubsItemDelivery {
    return {
      ...new SubsItemDelivery(),
      id: this.editForm.get(['id'])!.value,
      deliveryId: this.editForm.get(['deliveryId'])!.value,
      orderId: this.editForm.get(['orderId'])!.value,
      subscriptionId: this.editForm.get(['subscriptionId'])!.value,
      subscriptionItemId: this.editForm.get(['subscriptionItemId'])!.value,
      deliveryStatus: this.editForm.get(['deliveryStatus'])!.value,
      deliveryMethodId: this.editForm.get(['deliveryMethodId'])!.value,
      deliveryRefCode: this.editForm.get(['deliveryRefCode'])!.value,
      fileGenerationDate: this.editForm.get(['fileGenerationDate'])!.value
        ? moment(this.editForm.get(['fileGenerationDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      fileReceivedDate: this.editForm.get(['fileReceivedDate'])!.value
        ? moment(this.editForm.get(['fileReceivedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      deliveryDate: this.editForm.get(['deliveryDate'])!.value
        ? moment(this.editForm.get(['deliveryDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      remarks: this.editForm.get(['remarks'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      lastUpdatedDate: this.editForm.get(['lastUpdatedDate'])!.value
        ? moment(this.editForm.get(['lastUpdatedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastUpdatedBy: this.editForm.get(['lastUpdatedBy'])!.value,
      tenantId: this.editForm.get(['tenantId'])!.value,
      orderMaster: this.editForm.get(['orderMaster'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISubsItemDelivery>>): void {
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

  trackById(index: number, item: IOrderMaster): any {
    return item.id;
  }
}
