import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBillCycle, BillCycle } from 'app/shared/model/bill-cycle.model';
import { BillCycleService } from './bill-cycle.service';

@Component({
  selector: 'jhi-bill-cycle-update',
  templateUrl: './bill-cycle-update.component.html'
})
export class BillCycleUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    billCycleId: [null, [Validators.required]],
    billCycleDesc: [],
    billCycle: [null, [Validators.required]],
    billFrequency: [],
    billCycleStartDate: [],
    billCycleEndDate: [],
    dueDateOffset: [],
    directDebitProcessDay: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]]
  });

  constructor(protected billCycleService: BillCycleService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ billCycle }) => {
      if (!billCycle.id) {
        const today = moment().startOf('day');
        billCycle.billCycleStartDate = today;
        billCycle.billCycleEndDate = today;
        billCycle.createdDate = today;
        billCycle.lastUpdatedDate = today;
      }

      this.updateForm(billCycle);
    });
  }

  updateForm(billCycle: IBillCycle): void {
    this.editForm.patchValue({
      id: billCycle.id,
      billCycleId: billCycle.billCycleId,
      billCycleDesc: billCycle.billCycleDesc,
      billCycle: billCycle.billCycle,
      billFrequency: billCycle.billFrequency,
      billCycleStartDate: billCycle.billCycleStartDate ? billCycle.billCycleStartDate.format(DATE_TIME_FORMAT) : null,
      billCycleEndDate: billCycle.billCycleEndDate ? billCycle.billCycleEndDate.format(DATE_TIME_FORMAT) : null,
      dueDateOffset: billCycle.dueDateOffset,
      directDebitProcessDay: billCycle.directDebitProcessDay,
      createdDate: billCycle.createdDate ? billCycle.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: billCycle.createdBy,
      lastUpdatedDate: billCycle.lastUpdatedDate ? billCycle.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: billCycle.lastUpdatedBy,
      tenantId: billCycle.tenantId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const billCycle = this.createFromForm();
    if (billCycle.id !== undefined) {
      this.subscribeToSaveResponse(this.billCycleService.update(billCycle));
    } else {
      this.subscribeToSaveResponse(this.billCycleService.create(billCycle));
    }
  }

  private createFromForm(): IBillCycle {
    return {
      ...new BillCycle(),
      id: this.editForm.get(['id'])!.value,
      billCycleId: this.editForm.get(['billCycleId'])!.value,
      billCycleDesc: this.editForm.get(['billCycleDesc'])!.value,
      billCycle: this.editForm.get(['billCycle'])!.value,
      billFrequency: this.editForm.get(['billFrequency'])!.value,
      billCycleStartDate: this.editForm.get(['billCycleStartDate'])!.value
        ? moment(this.editForm.get(['billCycleStartDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      billCycleEndDate: this.editForm.get(['billCycleEndDate'])!.value
        ? moment(this.editForm.get(['billCycleEndDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      dueDateOffset: this.editForm.get(['dueDateOffset'])!.value,
      directDebitProcessDay: this.editForm.get(['directDebitProcessDay'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBillCycle>>): void {
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
