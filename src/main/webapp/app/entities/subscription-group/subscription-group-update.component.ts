import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISubscriptionGroup, SubscriptionGroup } from 'app/shared/model/subscription-group.model';
import { SubscriptionGroupService } from './subscription-group.service';
import { ICustomer } from 'app/shared/model/customer.model';
import { CustomerService } from 'app/entities/customer/customer.service';

@Component({
  selector: 'jhi-subscription-group-update',
  templateUrl: './subscription-group-update.component.html'
})
export class SubscriptionGroupUpdateComponent implements OnInit {
  isSaving = false;
  customers: ICustomer[] = [];

  editForm = this.fb.group({
    id: [],
    groupId: [null, [Validators.required]],
    custAcctId: [null, [Validators.required]],
    groupType: [null, [Validators.required]],
    groupName: [],
    status: [null, [Validators.required]],
    startDate: [],
    endDate: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]],
    customer: []
  });

  constructor(
    protected subscriptionGroupService: SubscriptionGroupService,
    protected customerService: CustomerService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ subscriptionGroup }) => {
      if (!subscriptionGroup.id) {
        const today = moment().startOf('day');
        subscriptionGroup.startDate = today;
        subscriptionGroup.endDate = today;
        subscriptionGroup.createdDate = today;
        subscriptionGroup.lastUpdatedDate = today;
      }

      this.updateForm(subscriptionGroup);

      this.customerService.query().subscribe((res: HttpResponse<ICustomer[]>) => (this.customers = res.body || []));
    });
  }

  updateForm(subscriptionGroup: ISubscriptionGroup): void {
    this.editForm.patchValue({
      id: subscriptionGroup.id,
      groupId: subscriptionGroup.groupId,
      custAcctId: subscriptionGroup.custAcctId,
      groupType: subscriptionGroup.groupType,
      groupName: subscriptionGroup.groupName,
      status: subscriptionGroup.status,
      startDate: subscriptionGroup.startDate ? subscriptionGroup.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: subscriptionGroup.endDate ? subscriptionGroup.endDate.format(DATE_TIME_FORMAT) : null,
      createdDate: subscriptionGroup.createdDate ? subscriptionGroup.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: subscriptionGroup.createdBy,
      lastUpdatedDate: subscriptionGroup.lastUpdatedDate ? subscriptionGroup.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: subscriptionGroup.lastUpdatedBy,
      tenantId: subscriptionGroup.tenantId,
      customer: subscriptionGroup.customer
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const subscriptionGroup = this.createFromForm();
    if (subscriptionGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.subscriptionGroupService.update(subscriptionGroup));
    } else {
      this.subscribeToSaveResponse(this.subscriptionGroupService.create(subscriptionGroup));
    }
  }

  private createFromForm(): ISubscriptionGroup {
    return {
      ...new SubscriptionGroup(),
      id: this.editForm.get(['id'])!.value,
      groupId: this.editForm.get(['groupId'])!.value,
      custAcctId: this.editForm.get(['custAcctId'])!.value,
      groupType: this.editForm.get(['groupType'])!.value,
      groupName: this.editForm.get(['groupName'])!.value,
      status: this.editForm.get(['status'])!.value,
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
      customer: this.editForm.get(['customer'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISubscriptionGroup>>): void {
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
