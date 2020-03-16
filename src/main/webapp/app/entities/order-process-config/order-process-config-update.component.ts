import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IOrderProcessConfig, OrderProcessConfig } from 'app/shared/model/order-process-config.model';
import { OrderProcessConfigService } from './order-process-config.service';

@Component({
  selector: 'jhi-order-process-config-update',
  templateUrl: './order-process-config-update.component.html'
})
export class OrderProcessConfigUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    orderType: [null, [Validators.required]],
    subOrderType: [null, [Validators.required]],
    orderNature: [],
    processName: [],
    childProcessName: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]]
  });

  constructor(
    protected orderProcessConfigService: OrderProcessConfigService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ orderProcessConfig }) => {
      if (!orderProcessConfig.id) {
        const today = moment().startOf('day');
        orderProcessConfig.createdDate = today;
        orderProcessConfig.lastUpdatedDate = today;
      }

      this.updateForm(orderProcessConfig);
    });
  }

  updateForm(orderProcessConfig: IOrderProcessConfig): void {
    this.editForm.patchValue({
      id: orderProcessConfig.id,
      orderType: orderProcessConfig.orderType,
      subOrderType: orderProcessConfig.subOrderType,
      orderNature: orderProcessConfig.orderNature,
      processName: orderProcessConfig.processName,
      childProcessName: orderProcessConfig.childProcessName,
      createdDate: orderProcessConfig.createdDate ? orderProcessConfig.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: orderProcessConfig.createdBy,
      lastUpdatedDate: orderProcessConfig.lastUpdatedDate ? orderProcessConfig.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: orderProcessConfig.lastUpdatedBy,
      tenantId: orderProcessConfig.tenantId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const orderProcessConfig = this.createFromForm();
    if (orderProcessConfig.id !== undefined) {
      this.subscribeToSaveResponse(this.orderProcessConfigService.update(orderProcessConfig));
    } else {
      this.subscribeToSaveResponse(this.orderProcessConfigService.create(orderProcessConfig));
    }
  }

  private createFromForm(): IOrderProcessConfig {
    return {
      ...new OrderProcessConfig(),
      id: this.editForm.get(['id'])!.value,
      orderType: this.editForm.get(['orderType'])!.value,
      subOrderType: this.editForm.get(['subOrderType'])!.value,
      orderNature: this.editForm.get(['orderNature'])!.value,
      processName: this.editForm.get(['processName'])!.value,
      childProcessName: this.editForm.get(['childProcessName'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrderProcessConfig>>): void {
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
