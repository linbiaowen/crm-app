import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISupremeMaster, SupremeMaster } from 'app/shared/model/supreme-master.model';
import { SupremeMasterService } from './supreme-master.service';
import { ICustSubscription } from 'app/shared/model/cust-subscription.model';
import { CustSubscriptionService } from 'app/entities/cust-subscription/cust-subscription.service';

@Component({
  selector: 'jhi-supreme-master-update',
  templateUrl: './supreme-master-update.component.html'
})
export class SupremeMasterUpdateComponent implements OnInit {
  isSaving = false;
  custsubscriptions: ICustSubscription[] = [];

  editForm = this.fb.group({
    id: [],
    supremeSeqId: [null, [Validators.required]],
    subscriptionId: [],
    msisdn: [],
    startDate: [],
    endDate: [],
    membershipServiceType: [],
    peCode: [],
    personalExecDirectLine: [],
    personalExecName: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]],
    custSubscription: []
  });

  constructor(
    protected supremeMasterService: SupremeMasterService,
    protected custSubscriptionService: CustSubscriptionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ supremeMaster }) => {
      if (!supremeMaster.id) {
        const today = moment().startOf('day');
        supremeMaster.startDate = today;
        supremeMaster.endDate = today;
        supremeMaster.createdDate = today;
        supremeMaster.lastUpdatedDate = today;
      }

      this.updateForm(supremeMaster);

      this.custSubscriptionService.query().subscribe((res: HttpResponse<ICustSubscription[]>) => (this.custsubscriptions = res.body || []));
    });
  }

  updateForm(supremeMaster: ISupremeMaster): void {
    this.editForm.patchValue({
      id: supremeMaster.id,
      supremeSeqId: supremeMaster.supremeSeqId,
      subscriptionId: supremeMaster.subscriptionId,
      msisdn: supremeMaster.msisdn,
      startDate: supremeMaster.startDate ? supremeMaster.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: supremeMaster.endDate ? supremeMaster.endDate.format(DATE_TIME_FORMAT) : null,
      membershipServiceType: supremeMaster.membershipServiceType,
      peCode: supremeMaster.peCode,
      personalExecDirectLine: supremeMaster.personalExecDirectLine,
      personalExecName: supremeMaster.personalExecName,
      createdDate: supremeMaster.createdDate ? supremeMaster.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: supremeMaster.createdBy,
      lastUpdatedDate: supremeMaster.lastUpdatedDate ? supremeMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: supremeMaster.lastUpdatedBy,
      tenantId: supremeMaster.tenantId,
      custSubscription: supremeMaster.custSubscription
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const supremeMaster = this.createFromForm();
    if (supremeMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.supremeMasterService.update(supremeMaster));
    } else {
      this.subscribeToSaveResponse(this.supremeMasterService.create(supremeMaster));
    }
  }

  private createFromForm(): ISupremeMaster {
    return {
      ...new SupremeMaster(),
      id: this.editForm.get(['id'])!.value,
      supremeSeqId: this.editForm.get(['supremeSeqId'])!.value,
      subscriptionId: this.editForm.get(['subscriptionId'])!.value,
      msisdn: this.editForm.get(['msisdn'])!.value,
      startDate: this.editForm.get(['startDate'])!.value ? moment(this.editForm.get(['startDate'])!.value, DATE_TIME_FORMAT) : undefined,
      endDate: this.editForm.get(['endDate'])!.value ? moment(this.editForm.get(['endDate'])!.value, DATE_TIME_FORMAT) : undefined,
      membershipServiceType: this.editForm.get(['membershipServiceType'])!.value,
      peCode: this.editForm.get(['peCode'])!.value,
      personalExecDirectLine: this.editForm.get(['personalExecDirectLine'])!.value,
      personalExecName: this.editForm.get(['personalExecName'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      lastUpdatedDate: this.editForm.get(['lastUpdatedDate'])!.value
        ? moment(this.editForm.get(['lastUpdatedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastUpdatedBy: this.editForm.get(['lastUpdatedBy'])!.value,
      tenantId: this.editForm.get(['tenantId'])!.value,
      custSubscription: this.editForm.get(['custSubscription'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISupremeMaster>>): void {
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

  trackById(index: number, item: ICustSubscription): any {
    return item.id;
  }
}
