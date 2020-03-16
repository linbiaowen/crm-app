import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISimInventory, SimInventory } from 'app/shared/model/sim-inventory.model';
import { SimInventoryService } from './sim-inventory.service';

@Component({
  selector: 'jhi-sim-inventory-update',
  templateUrl: './sim-inventory-update.component.html'
})
export class SimInventoryUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    iccid: [null, [Validators.required]],
    imsi: [],
    ki: [],
    k4sno: [],
    opsno: [],
    offerId: [],
    offerName: [],
    status: [],
    offerValidFromDate: [],
    offerValidToDate: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]]
  });

  constructor(protected simInventoryService: SimInventoryService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ simInventory }) => {
      if (!simInventory.id) {
        const today = moment().startOf('day');
        simInventory.createdDate = today;
        simInventory.lastUpdatedDate = today;
      }

      this.updateForm(simInventory);
    });
  }

  updateForm(simInventory: ISimInventory): void {
    this.editForm.patchValue({
      id: simInventory.id,
      iccid: simInventory.iccid,
      imsi: simInventory.imsi,
      ki: simInventory.ki,
      k4sno: simInventory.k4sno,
      opsno: simInventory.opsno,
      offerId: simInventory.offerId,
      offerName: simInventory.offerName,
      status: simInventory.status,
      offerValidFromDate: simInventory.offerValidFromDate,
      offerValidToDate: simInventory.offerValidToDate,
      createdDate: simInventory.createdDate ? simInventory.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: simInventory.createdBy,
      lastUpdatedDate: simInventory.lastUpdatedDate ? simInventory.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: simInventory.lastUpdatedBy,
      tenantId: simInventory.tenantId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const simInventory = this.createFromForm();
    if (simInventory.id !== undefined) {
      this.subscribeToSaveResponse(this.simInventoryService.update(simInventory));
    } else {
      this.subscribeToSaveResponse(this.simInventoryService.create(simInventory));
    }
  }

  private createFromForm(): ISimInventory {
    return {
      ...new SimInventory(),
      id: this.editForm.get(['id'])!.value,
      iccid: this.editForm.get(['iccid'])!.value,
      imsi: this.editForm.get(['imsi'])!.value,
      ki: this.editForm.get(['ki'])!.value,
      k4sno: this.editForm.get(['k4sno'])!.value,
      opsno: this.editForm.get(['opsno'])!.value,
      offerId: this.editForm.get(['offerId'])!.value,
      offerName: this.editForm.get(['offerName'])!.value,
      status: this.editForm.get(['status'])!.value,
      offerValidFromDate: this.editForm.get(['offerValidFromDate'])!.value,
      offerValidToDate: this.editForm.get(['offerValidToDate'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISimInventory>>): void {
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
