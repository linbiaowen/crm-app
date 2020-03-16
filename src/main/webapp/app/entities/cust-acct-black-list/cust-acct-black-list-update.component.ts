import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICustAcctBlackList, CustAcctBlackList } from 'app/shared/model/cust-acct-black-list.model';
import { CustAcctBlackListService } from './cust-acct-black-list.service';

@Component({
  selector: 'jhi-cust-acct-black-list-update',
  templateUrl: './cust-acct-black-list-update.component.html'
})
export class CustAcctBlackListUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    blackListId: [null, [Validators.required]],
    idType: [null, [Validators.required]],
    idNumber: [null, [Validators.required]],
    blackListCode: [null, [Validators.required]],
    startDate: [],
    endDate: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]]
  });

  constructor(
    protected custAcctBlackListService: CustAcctBlackListService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ custAcctBlackList }) => {
      if (!custAcctBlackList.id) {
        const today = moment().startOf('day');
        custAcctBlackList.startDate = today;
        custAcctBlackList.endDate = today;
        custAcctBlackList.createdDate = today;
        custAcctBlackList.lastUpdatedDate = today;
      }

      this.updateForm(custAcctBlackList);
    });
  }

  updateForm(custAcctBlackList: ICustAcctBlackList): void {
    this.editForm.patchValue({
      id: custAcctBlackList.id,
      blackListId: custAcctBlackList.blackListId,
      idType: custAcctBlackList.idType,
      idNumber: custAcctBlackList.idNumber,
      blackListCode: custAcctBlackList.blackListCode,
      startDate: custAcctBlackList.startDate ? custAcctBlackList.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: custAcctBlackList.endDate ? custAcctBlackList.endDate.format(DATE_TIME_FORMAT) : null,
      createdDate: custAcctBlackList.createdDate ? custAcctBlackList.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: custAcctBlackList.createdBy,
      lastUpdatedDate: custAcctBlackList.lastUpdatedDate ? custAcctBlackList.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: custAcctBlackList.lastUpdatedBy,
      tenantId: custAcctBlackList.tenantId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const custAcctBlackList = this.createFromForm();
    if (custAcctBlackList.id !== undefined) {
      this.subscribeToSaveResponse(this.custAcctBlackListService.update(custAcctBlackList));
    } else {
      this.subscribeToSaveResponse(this.custAcctBlackListService.create(custAcctBlackList));
    }
  }

  private createFromForm(): ICustAcctBlackList {
    return {
      ...new CustAcctBlackList(),
      id: this.editForm.get(['id'])!.value,
      blackListId: this.editForm.get(['blackListId'])!.value,
      idType: this.editForm.get(['idType'])!.value,
      idNumber: this.editForm.get(['idNumber'])!.value,
      blackListCode: this.editForm.get(['blackListCode'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustAcctBlackList>>): void {
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
