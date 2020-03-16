import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IOrderProcessStatusHistory, OrderProcessStatusHistory } from 'app/shared/model/order-process-status-history.model';
import { OrderProcessStatusHistoryService } from './order-process-status-history.service';

@Component({
  selector: 'jhi-order-process-status-history-update',
  templateUrl: './order-process-status-history-update.component.html'
})
export class OrderProcessStatusHistoryUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    orderId: [null, [Validators.required]],
    entryOrderStatus: [null, [Validators.required]],
    exitOrderStatus: [null, [Validators.required]],
    statusUpdatedDate: [null, [Validators.required]],
    processName: [],
    status: [],
    remarks: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]]
  });

  constructor(
    protected orderProcessStatusHistoryService: OrderProcessStatusHistoryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ orderProcessStatusHistory }) => {
      if (!orderProcessStatusHistory.id) {
        const today = moment().startOf('day');
        orderProcessStatusHistory.statusUpdatedDate = today;
        orderProcessStatusHistory.createdDate = today;
        orderProcessStatusHistory.lastUpdatedDate = today;
      }

      this.updateForm(orderProcessStatusHistory);
    });
  }

  updateForm(orderProcessStatusHistory: IOrderProcessStatusHistory): void {
    this.editForm.patchValue({
      id: orderProcessStatusHistory.id,
      orderId: orderProcessStatusHistory.orderId,
      entryOrderStatus: orderProcessStatusHistory.entryOrderStatus,
      exitOrderStatus: orderProcessStatusHistory.exitOrderStatus,
      statusUpdatedDate: orderProcessStatusHistory.statusUpdatedDate
        ? orderProcessStatusHistory.statusUpdatedDate.format(DATE_TIME_FORMAT)
        : null,
      processName: orderProcessStatusHistory.processName,
      status: orderProcessStatusHistory.status,
      remarks: orderProcessStatusHistory.remarks,
      createdDate: orderProcessStatusHistory.createdDate ? orderProcessStatusHistory.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: orderProcessStatusHistory.createdBy,
      lastUpdatedDate: orderProcessStatusHistory.lastUpdatedDate
        ? orderProcessStatusHistory.lastUpdatedDate.format(DATE_TIME_FORMAT)
        : null,
      lastUpdatedBy: orderProcessStatusHistory.lastUpdatedBy,
      tenantId: orderProcessStatusHistory.tenantId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const orderProcessStatusHistory = this.createFromForm();
    if (orderProcessStatusHistory.id !== undefined) {
      this.subscribeToSaveResponse(this.orderProcessStatusHistoryService.update(orderProcessStatusHistory));
    } else {
      this.subscribeToSaveResponse(this.orderProcessStatusHistoryService.create(orderProcessStatusHistory));
    }
  }

  private createFromForm(): IOrderProcessStatusHistory {
    return {
      ...new OrderProcessStatusHistory(),
      id: this.editForm.get(['id'])!.value,
      orderId: this.editForm.get(['orderId'])!.value,
      entryOrderStatus: this.editForm.get(['entryOrderStatus'])!.value,
      exitOrderStatus: this.editForm.get(['exitOrderStatus'])!.value,
      statusUpdatedDate: this.editForm.get(['statusUpdatedDate'])!.value
        ? moment(this.editForm.get(['statusUpdatedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      processName: this.editForm.get(['processName'])!.value,
      status: this.editForm.get(['status'])!.value,
      remarks: this.editForm.get(['remarks'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrderProcessStatusHistory>>): void {
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
