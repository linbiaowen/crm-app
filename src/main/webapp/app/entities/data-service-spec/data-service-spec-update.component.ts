import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IDataServiceSpec, DataServiceSpec } from 'app/shared/model/data-service-spec.model';
import { DataServiceSpecService } from './data-service-spec.service';

@Component({
  selector: 'jhi-data-service-spec-update',
  templateUrl: './data-service-spec-update.component.html'
})
export class DataServiceSpecUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    dataSpecId: [null, [Validators.required]],
    serviceSpecId: [null, [Validators.required]],
    maxEntitlement: [],
    maxAccessSpeed: [],
    throttledSpeed: [],
    upstreamSpeed: [],
    downstreamSpeed: [],
    socialSites: [],
    entertainmentPackOptions: [],
    roamingDataSpeed: [],
    roamingDataVolume: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]]
  });

  constructor(
    protected dataServiceSpecService: DataServiceSpecService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dataServiceSpec }) => {
      if (!dataServiceSpec.id) {
        const today = moment().startOf('day');
        dataServiceSpec.createdDate = today;
        dataServiceSpec.lastUpdatedDate = today;
      }

      this.updateForm(dataServiceSpec);
    });
  }

  updateForm(dataServiceSpec: IDataServiceSpec): void {
    this.editForm.patchValue({
      id: dataServiceSpec.id,
      dataSpecId: dataServiceSpec.dataSpecId,
      serviceSpecId: dataServiceSpec.serviceSpecId,
      maxEntitlement: dataServiceSpec.maxEntitlement,
      maxAccessSpeed: dataServiceSpec.maxAccessSpeed,
      throttledSpeed: dataServiceSpec.throttledSpeed,
      upstreamSpeed: dataServiceSpec.upstreamSpeed,
      downstreamSpeed: dataServiceSpec.downstreamSpeed,
      socialSites: dataServiceSpec.socialSites,
      entertainmentPackOptions: dataServiceSpec.entertainmentPackOptions,
      roamingDataSpeed: dataServiceSpec.roamingDataSpeed,
      roamingDataVolume: dataServiceSpec.roamingDataVolume,
      createdDate: dataServiceSpec.createdDate ? dataServiceSpec.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: dataServiceSpec.createdBy,
      lastUpdatedDate: dataServiceSpec.lastUpdatedDate ? dataServiceSpec.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: dataServiceSpec.lastUpdatedBy,
      tenantId: dataServiceSpec.tenantId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const dataServiceSpec = this.createFromForm();
    if (dataServiceSpec.id !== undefined) {
      this.subscribeToSaveResponse(this.dataServiceSpecService.update(dataServiceSpec));
    } else {
      this.subscribeToSaveResponse(this.dataServiceSpecService.create(dataServiceSpec));
    }
  }

  private createFromForm(): IDataServiceSpec {
    return {
      ...new DataServiceSpec(),
      id: this.editForm.get(['id'])!.value,
      dataSpecId: this.editForm.get(['dataSpecId'])!.value,
      serviceSpecId: this.editForm.get(['serviceSpecId'])!.value,
      maxEntitlement: this.editForm.get(['maxEntitlement'])!.value,
      maxAccessSpeed: this.editForm.get(['maxAccessSpeed'])!.value,
      throttledSpeed: this.editForm.get(['throttledSpeed'])!.value,
      upstreamSpeed: this.editForm.get(['upstreamSpeed'])!.value,
      downstreamSpeed: this.editForm.get(['downstreamSpeed'])!.value,
      socialSites: this.editForm.get(['socialSites'])!.value,
      entertainmentPackOptions: this.editForm.get(['entertainmentPackOptions'])!.value,
      roamingDataSpeed: this.editForm.get(['roamingDataSpeed'])!.value,
      roamingDataVolume: this.editForm.get(['roamingDataVolume'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDataServiceSpec>>): void {
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
