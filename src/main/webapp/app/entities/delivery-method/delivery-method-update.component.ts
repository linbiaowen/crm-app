import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IDeliveryMethod, DeliveryMethod } from 'app/shared/model/delivery-method.model';
import { DeliveryMethodService } from './delivery-method.service';

@Component({
  selector: 'jhi-delivery-method-update',
  templateUrl: './delivery-method-update.component.html'
})
export class DeliveryMethodUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    deliveryMethodId: [null, [Validators.required]],
    deliveryMethod: [null, [Validators.required]],
    deliveryMethodDesc: [],
    startDate: [],
    endDate: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]]
  });

  constructor(protected deliveryMethodService: DeliveryMethodService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ deliveryMethod }) => {
      if (!deliveryMethod.id) {
        const today = moment().startOf('day');
        deliveryMethod.startDate = today;
        deliveryMethod.endDate = today;
        deliveryMethod.createdDate = today;
        deliveryMethod.lastUpdatedDate = today;
      }

      this.updateForm(deliveryMethod);
    });
  }

  updateForm(deliveryMethod: IDeliveryMethod): void {
    this.editForm.patchValue({
      id: deliveryMethod.id,
      deliveryMethodId: deliveryMethod.deliveryMethodId,
      deliveryMethod: deliveryMethod.deliveryMethod,
      deliveryMethodDesc: deliveryMethod.deliveryMethodDesc,
      startDate: deliveryMethod.startDate ? deliveryMethod.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: deliveryMethod.endDate ? deliveryMethod.endDate.format(DATE_TIME_FORMAT) : null,
      createdDate: deliveryMethod.createdDate ? deliveryMethod.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: deliveryMethod.createdBy,
      lastUpdatedDate: deliveryMethod.lastUpdatedDate ? deliveryMethod.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: deliveryMethod.lastUpdatedBy,
      tenantId: deliveryMethod.tenantId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const deliveryMethod = this.createFromForm();
    if (deliveryMethod.id !== undefined) {
      this.subscribeToSaveResponse(this.deliveryMethodService.update(deliveryMethod));
    } else {
      this.subscribeToSaveResponse(this.deliveryMethodService.create(deliveryMethod));
    }
  }

  private createFromForm(): IDeliveryMethod {
    return {
      ...new DeliveryMethod(),
      id: this.editForm.get(['id'])!.value,
      deliveryMethodId: this.editForm.get(['deliveryMethodId'])!.value,
      deliveryMethod: this.editForm.get(['deliveryMethod'])!.value,
      deliveryMethodDesc: this.editForm.get(['deliveryMethodDesc'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDeliveryMethod>>): void {
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
