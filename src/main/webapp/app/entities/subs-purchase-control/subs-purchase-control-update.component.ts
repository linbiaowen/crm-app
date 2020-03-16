import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISubsPurchaseControl, SubsPurchaseControl } from 'app/shared/model/subs-purchase-control.model';
import { SubsPurchaseControlService } from './subs-purchase-control.service';

@Component({
  selector: 'jhi-subs-purchase-control-update',
  templateUrl: './subs-purchase-control-update.component.html'
})
export class SubsPurchaseControlUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    subscriptionId: [null, [Validators.required]],
    serviceType: [null, [Validators.required]],
    purchaseControlFlag: [null, [Validators.required]],
    status: [null, [Validators.required]],
    startDate: [],
    endDate: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]]
  });

  constructor(
    protected subsPurchaseControlService: SubsPurchaseControlService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ subsPurchaseControl }) => {
      if (!subsPurchaseControl.id) {
        const today = moment().startOf('day');
        subsPurchaseControl.startDate = today;
        subsPurchaseControl.endDate = today;
        subsPurchaseControl.createdDate = today;
        subsPurchaseControl.lastUpdatedDate = today;
      }

      this.updateForm(subsPurchaseControl);
    });
  }

  updateForm(subsPurchaseControl: ISubsPurchaseControl): void {
    this.editForm.patchValue({
      id: subsPurchaseControl.id,
      subscriptionId: subsPurchaseControl.subscriptionId,
      serviceType: subsPurchaseControl.serviceType,
      purchaseControlFlag: subsPurchaseControl.purchaseControlFlag,
      status: subsPurchaseControl.status,
      startDate: subsPurchaseControl.startDate ? subsPurchaseControl.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: subsPurchaseControl.endDate ? subsPurchaseControl.endDate.format(DATE_TIME_FORMAT) : null,
      createdDate: subsPurchaseControl.createdDate ? subsPurchaseControl.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: subsPurchaseControl.createdBy,
      lastUpdatedDate: subsPurchaseControl.lastUpdatedDate ? subsPurchaseControl.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: subsPurchaseControl.lastUpdatedBy,
      tenantId: subsPurchaseControl.tenantId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const subsPurchaseControl = this.createFromForm();
    if (subsPurchaseControl.id !== undefined) {
      this.subscribeToSaveResponse(this.subsPurchaseControlService.update(subsPurchaseControl));
    } else {
      this.subscribeToSaveResponse(this.subsPurchaseControlService.create(subsPurchaseControl));
    }
  }

  private createFromForm(): ISubsPurchaseControl {
    return {
      ...new SubsPurchaseControl(),
      id: this.editForm.get(['id'])!.value,
      subscriptionId: this.editForm.get(['subscriptionId'])!.value,
      serviceType: this.editForm.get(['serviceType'])!.value,
      purchaseControlFlag: this.editForm.get(['purchaseControlFlag'])!.value,
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
      tenantId: this.editForm.get(['tenantId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISubsPurchaseControl>>): void {
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
