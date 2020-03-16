import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IOrderProcessStatus, OrderProcessStatus } from 'app/shared/model/order-process-status.model';
import { OrderProcessStatusService } from './order-process-status.service';
import { IOrderMaster } from 'app/shared/model/order-master.model';
import { OrderMasterService } from 'app/entities/order-master/order-master.service';

@Component({
  selector: 'jhi-order-process-status-update',
  templateUrl: './order-process-status-update.component.html'
})
export class OrderProcessStatusUpdateComponent implements OnInit {
  isSaving = false;
  ordermasters: IOrderMaster[] = [];

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
    tenantId: [null, [Validators.required]],
    orderMaster: []
  });

  constructor(
    protected orderProcessStatusService: OrderProcessStatusService,
    protected orderMasterService: OrderMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ orderProcessStatus }) => {
      if (!orderProcessStatus.id) {
        const today = moment().startOf('day');
        orderProcessStatus.statusUpdatedDate = today;
        orderProcessStatus.createdDate = today;
        orderProcessStatus.lastUpdatedDate = today;
      }

      this.updateForm(orderProcessStatus);

      this.orderMasterService.query().subscribe((res: HttpResponse<IOrderMaster[]>) => (this.ordermasters = res.body || []));
    });
  }

  updateForm(orderProcessStatus: IOrderProcessStatus): void {
    this.editForm.patchValue({
      id: orderProcessStatus.id,
      orderId: orderProcessStatus.orderId,
      entryOrderStatus: orderProcessStatus.entryOrderStatus,
      exitOrderStatus: orderProcessStatus.exitOrderStatus,
      statusUpdatedDate: orderProcessStatus.statusUpdatedDate ? orderProcessStatus.statusUpdatedDate.format(DATE_TIME_FORMAT) : null,
      processName: orderProcessStatus.processName,
      status: orderProcessStatus.status,
      remarks: orderProcessStatus.remarks,
      createdDate: orderProcessStatus.createdDate ? orderProcessStatus.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: orderProcessStatus.createdBy,
      lastUpdatedDate: orderProcessStatus.lastUpdatedDate ? orderProcessStatus.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: orderProcessStatus.lastUpdatedBy,
      tenantId: orderProcessStatus.tenantId,
      orderMaster: orderProcessStatus.orderMaster
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const orderProcessStatus = this.createFromForm();
    if (orderProcessStatus.id !== undefined) {
      this.subscribeToSaveResponse(this.orderProcessStatusService.update(orderProcessStatus));
    } else {
      this.subscribeToSaveResponse(this.orderProcessStatusService.create(orderProcessStatus));
    }
  }

  private createFromForm(): IOrderProcessStatus {
    return {
      ...new OrderProcessStatus(),
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
      tenantId: this.editForm.get(['tenantId'])!.value,
      orderMaster: this.editForm.get(['orderMaster'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrderProcessStatus>>): void {
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
