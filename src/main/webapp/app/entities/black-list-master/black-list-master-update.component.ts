import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBlackListMaster, BlackListMaster } from 'app/shared/model/black-list-master.model';
import { BlackListMasterService } from './black-list-master.service';

@Component({
  selector: 'jhi-black-list-master-update',
  templateUrl: './black-list-master-update.component.html'
})
export class BlackListMasterUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    blackListCode: [null, [Validators.required]],
    blackListReason: [null, [Validators.required]],
    startDate: [],
    endDate: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]]
  });

  constructor(
    protected blackListMasterService: BlackListMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ blackListMaster }) => {
      if (!blackListMaster.id) {
        const today = moment().startOf('day');
        blackListMaster.startDate = today;
        blackListMaster.endDate = today;
        blackListMaster.createdDate = today;
        blackListMaster.lastUpdatedDate = today;
      }

      this.updateForm(blackListMaster);
    });
  }

  updateForm(blackListMaster: IBlackListMaster): void {
    this.editForm.patchValue({
      id: blackListMaster.id,
      blackListCode: blackListMaster.blackListCode,
      blackListReason: blackListMaster.blackListReason,
      startDate: blackListMaster.startDate ? blackListMaster.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: blackListMaster.endDate ? blackListMaster.endDate.format(DATE_TIME_FORMAT) : null,
      createdDate: blackListMaster.createdDate ? blackListMaster.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: blackListMaster.createdBy,
      lastUpdatedDate: blackListMaster.lastUpdatedDate ? blackListMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: blackListMaster.lastUpdatedBy,
      tenantId: blackListMaster.tenantId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const blackListMaster = this.createFromForm();
    if (blackListMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.blackListMasterService.update(blackListMaster));
    } else {
      this.subscribeToSaveResponse(this.blackListMasterService.create(blackListMaster));
    }
  }

  private createFromForm(): IBlackListMaster {
    return {
      ...new BlackListMaster(),
      id: this.editForm.get(['id'])!.value,
      blackListCode: this.editForm.get(['blackListCode'])!.value,
      blackListReason: this.editForm.get(['blackListReason'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBlackListMaster>>): void {
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
