import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISubscriptionProvision, SubscriptionProvision } from 'app/shared/model/subscription-provision.model';
import { SubscriptionProvisionService } from './subscription-provision.service';
import { IOrderMaster } from 'app/shared/model/order-master.model';
import { OrderMasterService } from 'app/entities/order-master/order-master.service';

@Component({
  selector: 'jhi-subscription-provision-update',
  templateUrl: './subscription-provision-update.component.html'
})
export class SubscriptionProvisionUpdateComponent implements OnInit {
  isSaving = false;
  ordermasters: IOrderMaster[] = [];

  editForm = this.fb.group({
    id: [],
    provisionSeqId: [null, [Validators.required]],
    subscriptionId: [null, [Validators.required]],
    orderId: [null, [Validators.required]],
    productId: [null, [Validators.required]],
    msisdn: [null, [Validators.required]],
    iccid: [null, [Validators.required]],
    imsi: [],
    serviceSpecId: [null, [Validators.required]],
    resourceSpecId: [null, [Validators.required]],
    rfs: [null, [Validators.required]],
    provisionStatus: [null, [Validators.required]],
    provisionStatusDesc: [],
    provisionResponse: [],
    startDate: [],
    endDate: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]],
    orderMaster: []
  });

  constructor(
    protected subscriptionProvisionService: SubscriptionProvisionService,
    protected orderMasterService: OrderMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ subscriptionProvision }) => {
      if (!subscriptionProvision.id) {
        const today = moment().startOf('day');
        subscriptionProvision.startDate = today;
        subscriptionProvision.endDate = today;
        subscriptionProvision.createdDate = today;
        subscriptionProvision.lastUpdatedDate = today;
      }

      this.updateForm(subscriptionProvision);

      this.orderMasterService.query().subscribe((res: HttpResponse<IOrderMaster[]>) => (this.ordermasters = res.body || []));
    });
  }

  updateForm(subscriptionProvision: ISubscriptionProvision): void {
    this.editForm.patchValue({
      id: subscriptionProvision.id,
      provisionSeqId: subscriptionProvision.provisionSeqId,
      subscriptionId: subscriptionProvision.subscriptionId,
      orderId: subscriptionProvision.orderId,
      productId: subscriptionProvision.productId,
      msisdn: subscriptionProvision.msisdn,
      iccid: subscriptionProvision.iccid,
      imsi: subscriptionProvision.imsi,
      serviceSpecId: subscriptionProvision.serviceSpecId,
      resourceSpecId: subscriptionProvision.resourceSpecId,
      rfs: subscriptionProvision.rfs,
      provisionStatus: subscriptionProvision.provisionStatus,
      provisionStatusDesc: subscriptionProvision.provisionStatusDesc,
      provisionResponse: subscriptionProvision.provisionResponse,
      startDate: subscriptionProvision.startDate ? subscriptionProvision.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: subscriptionProvision.endDate ? subscriptionProvision.endDate.format(DATE_TIME_FORMAT) : null,
      createdDate: subscriptionProvision.createdDate ? subscriptionProvision.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: subscriptionProvision.createdBy,
      lastUpdatedDate: subscriptionProvision.lastUpdatedDate ? subscriptionProvision.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: subscriptionProvision.lastUpdatedBy,
      tenantId: subscriptionProvision.tenantId,
      orderMaster: subscriptionProvision.orderMaster
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const subscriptionProvision = this.createFromForm();
    if (subscriptionProvision.id !== undefined) {
      this.subscribeToSaveResponse(this.subscriptionProvisionService.update(subscriptionProvision));
    } else {
      this.subscribeToSaveResponse(this.subscriptionProvisionService.create(subscriptionProvision));
    }
  }

  private createFromForm(): ISubscriptionProvision {
    return {
      ...new SubscriptionProvision(),
      id: this.editForm.get(['id'])!.value,
      provisionSeqId: this.editForm.get(['provisionSeqId'])!.value,
      subscriptionId: this.editForm.get(['subscriptionId'])!.value,
      orderId: this.editForm.get(['orderId'])!.value,
      productId: this.editForm.get(['productId'])!.value,
      msisdn: this.editForm.get(['msisdn'])!.value,
      iccid: this.editForm.get(['iccid'])!.value,
      imsi: this.editForm.get(['imsi'])!.value,
      serviceSpecId: this.editForm.get(['serviceSpecId'])!.value,
      resourceSpecId: this.editForm.get(['resourceSpecId'])!.value,
      rfs: this.editForm.get(['rfs'])!.value,
      provisionStatus: this.editForm.get(['provisionStatus'])!.value,
      provisionStatusDesc: this.editForm.get(['provisionStatusDesc'])!.value,
      provisionResponse: this.editForm.get(['provisionResponse'])!.value,
      startDate: this.editForm.get(['startDate'])!.value ? moment(this.editForm.get(['startDate'])!.value, DATE_TIME_FORMAT) : undefined,
      endDate: this.editForm.get(['endDate'])!.value ? moment(this.editForm.get(['endDate'])!.value, DATE_TIME_FORMAT) : undefined,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISubscriptionProvision>>): void {
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
