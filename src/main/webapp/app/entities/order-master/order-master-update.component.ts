import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IOrderMaster, OrderMaster } from 'app/shared/model/order-master.model';
import { OrderMasterService } from './order-master.service';
import { ICustomer } from 'app/shared/model/customer.model';
import { CustomerService } from 'app/entities/customer/customer.service';
import { ICustSubscription } from 'app/shared/model/cust-subscription.model';
import { CustSubscriptionService } from 'app/entities/cust-subscription/cust-subscription.service';

type SelectableEntity = ICustomer | ICustSubscription;

@Component({
  selector: 'jhi-order-master-update',
  templateUrl: './order-master-update.component.html'
})
export class OrderMasterUpdateComponent implements OnInit {
  isSaving = false;
  customers: ICustomer[] = [];
  custsubscriptions: ICustSubscription[] = [];

  editForm = this.fb.group({
    id: [],
    orderId: [null, [Validators.required]],
    custAcctId: [null, [Validators.required]],
    subscriptionId: [null, [Validators.required]],
    orderType: [null, [Validators.required]],
    subOrderType: [null, [Validators.required]],
    orderNature: [],
    isChangePlan: [],
    orderStatus: [null, [Validators.required]],
    remarks: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]],
    customer: [],
    custSubscription: []
  });

  constructor(
    protected orderMasterService: OrderMasterService,
    protected customerService: CustomerService,
    protected custSubscriptionService: CustSubscriptionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ orderMaster }) => {
      if (!orderMaster.id) {
        const today = moment().startOf('day');
        orderMaster.createdDate = today;
        orderMaster.lastUpdatedDate = today;
      }

      this.updateForm(orderMaster);

      this.customerService.query().subscribe((res: HttpResponse<ICustomer[]>) => (this.customers = res.body || []));

      this.custSubscriptionService.query().subscribe((res: HttpResponse<ICustSubscription[]>) => (this.custsubscriptions = res.body || []));
    });
  }

  updateForm(orderMaster: IOrderMaster): void {
    this.editForm.patchValue({
      id: orderMaster.id,
      orderId: orderMaster.orderId,
      custAcctId: orderMaster.custAcctId,
      subscriptionId: orderMaster.subscriptionId,
      orderType: orderMaster.orderType,
      subOrderType: orderMaster.subOrderType,
      orderNature: orderMaster.orderNature,
      isChangePlan: orderMaster.isChangePlan,
      orderStatus: orderMaster.orderStatus,
      remarks: orderMaster.remarks,
      createdDate: orderMaster.createdDate ? orderMaster.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: orderMaster.createdBy,
      lastUpdatedDate: orderMaster.lastUpdatedDate ? orderMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: orderMaster.lastUpdatedBy,
      tenantId: orderMaster.tenantId,
      customer: orderMaster.customer,
      custSubscription: orderMaster.custSubscription
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const orderMaster = this.createFromForm();
    if (orderMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.orderMasterService.update(orderMaster));
    } else {
      this.subscribeToSaveResponse(this.orderMasterService.create(orderMaster));
    }
  }

  private createFromForm(): IOrderMaster {
    return {
      ...new OrderMaster(),
      id: this.editForm.get(['id'])!.value,
      orderId: this.editForm.get(['orderId'])!.value,
      custAcctId: this.editForm.get(['custAcctId'])!.value,
      subscriptionId: this.editForm.get(['subscriptionId'])!.value,
      orderType: this.editForm.get(['orderType'])!.value,
      subOrderType: this.editForm.get(['subOrderType'])!.value,
      orderNature: this.editForm.get(['orderNature'])!.value,
      isChangePlan: this.editForm.get(['isChangePlan'])!.value,
      orderStatus: this.editForm.get(['orderStatus'])!.value,
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
      customer: this.editForm.get(['customer'])!.value,
      custSubscription: this.editForm.get(['custSubscription'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrderMaster>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
