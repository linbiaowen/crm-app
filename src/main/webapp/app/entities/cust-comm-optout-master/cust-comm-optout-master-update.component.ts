import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICustCommOptoutMaster, CustCommOptoutMaster } from 'app/shared/model/cust-comm-optout-master.model';
import { CustCommOptoutMasterService } from './cust-comm-optout-master.service';
import { ICustomer } from 'app/shared/model/customer.model';
import { CustomerService } from 'app/entities/customer/customer.service';
import { ICustSubscription } from 'app/shared/model/cust-subscription.model';
import { CustSubscriptionService } from 'app/entities/cust-subscription/cust-subscription.service';

type SelectableEntity = ICustomer | ICustSubscription;

@Component({
  selector: 'jhi-cust-comm-optout-master-update',
  templateUrl: './cust-comm-optout-master-update.component.html'
})
export class CustCommOptoutMasterUpdateComponent implements OnInit {
  isSaving = false;
  customers: ICustomer[] = [];
  custsubscriptions: ICustSubscription[] = [];

  editForm = this.fb.group({
    id: [],
    optoutId: [null, [Validators.required]],
    custAcctId: [],
    subscriptionId: [],
    primaryMobNbr: [],
    optoutTypeId: [null, [Validators.required]],
    optoutMediaId: [null, [Validators.required]],
    optoutStatus: [null, [Validators.required]],
    optoutStartDate: [],
    optoutEndDate: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]],
    customer: [],
    custSubscription: []
  });

  constructor(
    protected custCommOptoutMasterService: CustCommOptoutMasterService,
    protected customerService: CustomerService,
    protected custSubscriptionService: CustSubscriptionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ custCommOptoutMaster }) => {
      if (!custCommOptoutMaster.id) {
        const today = moment().startOf('day');
        custCommOptoutMaster.optoutStartDate = today;
        custCommOptoutMaster.optoutEndDate = today;
        custCommOptoutMaster.createdDate = today;
        custCommOptoutMaster.lastUpdatedDate = today;
      }

      this.updateForm(custCommOptoutMaster);

      this.customerService.query().subscribe((res: HttpResponse<ICustomer[]>) => (this.customers = res.body || []));

      this.custSubscriptionService.query().subscribe((res: HttpResponse<ICustSubscription[]>) => (this.custsubscriptions = res.body || []));
    });
  }

  updateForm(custCommOptoutMaster: ICustCommOptoutMaster): void {
    this.editForm.patchValue({
      id: custCommOptoutMaster.id,
      optoutId: custCommOptoutMaster.optoutId,
      custAcctId: custCommOptoutMaster.custAcctId,
      subscriptionId: custCommOptoutMaster.subscriptionId,
      primaryMobNbr: custCommOptoutMaster.primaryMobNbr,
      optoutTypeId: custCommOptoutMaster.optoutTypeId,
      optoutMediaId: custCommOptoutMaster.optoutMediaId,
      optoutStatus: custCommOptoutMaster.optoutStatus,
      optoutStartDate: custCommOptoutMaster.optoutStartDate ? custCommOptoutMaster.optoutStartDate.format(DATE_TIME_FORMAT) : null,
      optoutEndDate: custCommOptoutMaster.optoutEndDate ? custCommOptoutMaster.optoutEndDate.format(DATE_TIME_FORMAT) : null,
      createdDate: custCommOptoutMaster.createdDate ? custCommOptoutMaster.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: custCommOptoutMaster.createdBy,
      lastUpdatedDate: custCommOptoutMaster.lastUpdatedDate ? custCommOptoutMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: custCommOptoutMaster.lastUpdatedBy,
      tenantId: custCommOptoutMaster.tenantId,
      customer: custCommOptoutMaster.customer,
      custSubscription: custCommOptoutMaster.custSubscription
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const custCommOptoutMaster = this.createFromForm();
    if (custCommOptoutMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.custCommOptoutMasterService.update(custCommOptoutMaster));
    } else {
      this.subscribeToSaveResponse(this.custCommOptoutMasterService.create(custCommOptoutMaster));
    }
  }

  private createFromForm(): ICustCommOptoutMaster {
    return {
      ...new CustCommOptoutMaster(),
      id: this.editForm.get(['id'])!.value,
      optoutId: this.editForm.get(['optoutId'])!.value,
      custAcctId: this.editForm.get(['custAcctId'])!.value,
      subscriptionId: this.editForm.get(['subscriptionId'])!.value,
      primaryMobNbr: this.editForm.get(['primaryMobNbr'])!.value,
      optoutTypeId: this.editForm.get(['optoutTypeId'])!.value,
      optoutMediaId: this.editForm.get(['optoutMediaId'])!.value,
      optoutStatus: this.editForm.get(['optoutStatus'])!.value,
      optoutStartDate: this.editForm.get(['optoutStartDate'])!.value
        ? moment(this.editForm.get(['optoutStartDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      optoutEndDate: this.editForm.get(['optoutEndDate'])!.value
        ? moment(this.editForm.get(['optoutEndDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustCommOptoutMaster>>): void {
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
