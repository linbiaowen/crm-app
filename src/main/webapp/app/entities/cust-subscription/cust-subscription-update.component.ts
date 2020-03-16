import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICustSubscription, CustSubscription } from 'app/shared/model/cust-subscription.model';
import { CustSubscriptionService } from './cust-subscription.service';
import { ICustomer } from 'app/shared/model/customer.model';
import { CustomerService } from 'app/entities/customer/customer.service';

@Component({
  selector: 'jhi-cust-subscription-update',
  templateUrl: './cust-subscription-update.component.html'
})
export class CustSubscriptionUpdateComponent implements OnInit {
  isSaving = false;
  customers: ICustomer[] = [];

  editForm = this.fb.group({
    id: [],
    subscriptionId: [null, [Validators.required]],
    activationDate: [],
    subsEndDate: [],
    subsPurchaseDate: [],
    origServiceStartDate: [],
    status: [null, [Validators.required]],
    primarySubsInd: [],
    subsLastStatusCode: [],
    lastStatusUpdatedDate: [],
    custAcctId: [],
    billingAcctId: [],
    billCycleId: [],
    orderId: [],
    matrixxObjectId: [],
    subscriberName: [],
    subsDeptName: [],
    selfCarePassword: [],
    subsCategory: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]],
    customer: []
  });

  constructor(
    protected custSubscriptionService: CustSubscriptionService,
    protected customerService: CustomerService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ custSubscription }) => {
      if (!custSubscription.id) {
        const today = moment().startOf('day');
        custSubscription.activationDate = today;
        custSubscription.subsEndDate = today;
        custSubscription.subsPurchaseDate = today;
        custSubscription.origServiceStartDate = today;
        custSubscription.lastStatusUpdatedDate = today;
        custSubscription.createdDate = today;
        custSubscription.lastUpdatedDate = today;
      }

      this.updateForm(custSubscription);

      this.customerService.query().subscribe((res: HttpResponse<ICustomer[]>) => (this.customers = res.body || []));
    });
  }

  updateForm(custSubscription: ICustSubscription): void {
    this.editForm.patchValue({
      id: custSubscription.id,
      subscriptionId: custSubscription.subscriptionId,
      activationDate: custSubscription.activationDate ? custSubscription.activationDate.format(DATE_TIME_FORMAT) : null,
      subsEndDate: custSubscription.subsEndDate ? custSubscription.subsEndDate.format(DATE_TIME_FORMAT) : null,
      subsPurchaseDate: custSubscription.subsPurchaseDate ? custSubscription.subsPurchaseDate.format(DATE_TIME_FORMAT) : null,
      origServiceStartDate: custSubscription.origServiceStartDate ? custSubscription.origServiceStartDate.format(DATE_TIME_FORMAT) : null,
      status: custSubscription.status,
      primarySubsInd: custSubscription.primarySubsInd,
      subsLastStatusCode: custSubscription.subsLastStatusCode,
      lastStatusUpdatedDate: custSubscription.lastStatusUpdatedDate
        ? custSubscription.lastStatusUpdatedDate.format(DATE_TIME_FORMAT)
        : null,
      custAcctId: custSubscription.custAcctId,
      billingAcctId: custSubscription.billingAcctId,
      billCycleId: custSubscription.billCycleId,
      orderId: custSubscription.orderId,
      matrixxObjectId: custSubscription.matrixxObjectId,
      subscriberName: custSubscription.subscriberName,
      subsDeptName: custSubscription.subsDeptName,
      selfCarePassword: custSubscription.selfCarePassword,
      subsCategory: custSubscription.subsCategory,
      createdDate: custSubscription.createdDate ? custSubscription.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: custSubscription.createdBy,
      lastUpdatedDate: custSubscription.lastUpdatedDate ? custSubscription.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: custSubscription.lastUpdatedBy,
      tenantId: custSubscription.tenantId,
      customer: custSubscription.customer
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const custSubscription = this.createFromForm();
    if (custSubscription.id !== undefined) {
      this.subscribeToSaveResponse(this.custSubscriptionService.update(custSubscription));
    } else {
      this.subscribeToSaveResponse(this.custSubscriptionService.create(custSubscription));
    }
  }

  private createFromForm(): ICustSubscription {
    return {
      ...new CustSubscription(),
      id: this.editForm.get(['id'])!.value,
      subscriptionId: this.editForm.get(['subscriptionId'])!.value,
      activationDate: this.editForm.get(['activationDate'])!.value
        ? moment(this.editForm.get(['activationDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      subsEndDate: this.editForm.get(['subsEndDate'])!.value
        ? moment(this.editForm.get(['subsEndDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      subsPurchaseDate: this.editForm.get(['subsPurchaseDate'])!.value
        ? moment(this.editForm.get(['subsPurchaseDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      origServiceStartDate: this.editForm.get(['origServiceStartDate'])!.value
        ? moment(this.editForm.get(['origServiceStartDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      status: this.editForm.get(['status'])!.value,
      primarySubsInd: this.editForm.get(['primarySubsInd'])!.value,
      subsLastStatusCode: this.editForm.get(['subsLastStatusCode'])!.value,
      lastStatusUpdatedDate: this.editForm.get(['lastStatusUpdatedDate'])!.value
        ? moment(this.editForm.get(['lastStatusUpdatedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      custAcctId: this.editForm.get(['custAcctId'])!.value,
      billingAcctId: this.editForm.get(['billingAcctId'])!.value,
      billCycleId: this.editForm.get(['billCycleId'])!.value,
      orderId: this.editForm.get(['orderId'])!.value,
      matrixxObjectId: this.editForm.get(['matrixxObjectId'])!.value,
      subscriberName: this.editForm.get(['subscriberName'])!.value,
      subsDeptName: this.editForm.get(['subsDeptName'])!.value,
      selfCarePassword: this.editForm.get(['selfCarePassword'])!.value,
      subsCategory: this.editForm.get(['subsCategory'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      lastUpdatedDate: this.editForm.get(['lastUpdatedDate'])!.value
        ? moment(this.editForm.get(['lastUpdatedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastUpdatedBy: this.editForm.get(['lastUpdatedBy'])!.value,
      tenantId: this.editForm.get(['tenantId'])!.value,
      customer: this.editForm.get(['customer'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustSubscription>>): void {
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

  trackById(index: number, item: ICustomer): any {
    return item.id;
  }
}
