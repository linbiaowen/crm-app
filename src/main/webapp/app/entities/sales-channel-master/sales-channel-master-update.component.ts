import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISalesChannelMaster, SalesChannelMaster } from 'app/shared/model/sales-channel-master.model';
import { SalesChannelMasterService } from './sales-channel-master.service';

@Component({
  selector: 'jhi-sales-channel-master-update',
  templateUrl: './sales-channel-master-update.component.html'
})
export class SalesChannelMasterUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    salesChannel: [null, [Validators.required]],
    salesChannelDesc: [],
    startDate: [],
    endDate: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]]
  });

  constructor(
    protected salesChannelMasterService: SalesChannelMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ salesChannelMaster }) => {
      if (!salesChannelMaster.id) {
        const today = moment().startOf('day');
        salesChannelMaster.startDate = today;
        salesChannelMaster.endDate = today;
        salesChannelMaster.createdDate = today;
        salesChannelMaster.lastUpdatedDate = today;
      }

      this.updateForm(salesChannelMaster);
    });
  }

  updateForm(salesChannelMaster: ISalesChannelMaster): void {
    this.editForm.patchValue({
      id: salesChannelMaster.id,
      salesChannel: salesChannelMaster.salesChannel,
      salesChannelDesc: salesChannelMaster.salesChannelDesc,
      startDate: salesChannelMaster.startDate ? salesChannelMaster.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: salesChannelMaster.endDate ? salesChannelMaster.endDate.format(DATE_TIME_FORMAT) : null,
      createdDate: salesChannelMaster.createdDate ? salesChannelMaster.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: salesChannelMaster.createdBy,
      lastUpdatedDate: salesChannelMaster.lastUpdatedDate ? salesChannelMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: salesChannelMaster.lastUpdatedBy,
      tenantId: salesChannelMaster.tenantId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const salesChannelMaster = this.createFromForm();
    if (salesChannelMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.salesChannelMasterService.update(salesChannelMaster));
    } else {
      this.subscribeToSaveResponse(this.salesChannelMasterService.create(salesChannelMaster));
    }
  }

  private createFromForm(): ISalesChannelMaster {
    return {
      ...new SalesChannelMaster(),
      id: this.editForm.get(['id'])!.value,
      salesChannel: this.editForm.get(['salesChannel'])!.value,
      salesChannelDesc: this.editForm.get(['salesChannelDesc'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISalesChannelMaster>>): void {
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
