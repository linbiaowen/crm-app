import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IModelGroup, ModelGroup } from 'app/shared/model/model-group.model';
import { ModelGroupService } from './model-group.service';

@Component({
  selector: 'jhi-model-group-update',
  templateUrl: './model-group-update.component.html'
})
export class ModelGroupUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    modelGroup: [null, [Validators.required]],
    groupDesc: [],
    listPrice: [],
    brand: [],
    model: [],
    origCountry: [],
    network: [],
    camera: [],
    memCardSlot: [],
    dataTransfer: [],
    warranty: [],
    warrantyProvider: [],
    modelCate: [],
    remarks: [],
    remarksEndDate: [],
    brandChi: [],
    modelChi: [],
    origCountryChi: [],
    networkChi: [],
    cameraChi: [],
    memCardSlotChi: [],
    dataTransferChi: [],
    warrantyChi: [],
    warrantyProviderChi: [],
    modelCateChi: [],
    remarksChi: [],
    remarksChiEndDate: [],
    couponFlag: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]]
  });

  constructor(protected modelGroupService: ModelGroupService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ modelGroup }) => {
      if (!modelGroup.id) {
        const today = moment().startOf('day');
        modelGroup.createdDate = today;
        modelGroup.lastUpdatedDate = today;
      }

      this.updateForm(modelGroup);
    });
  }

  updateForm(modelGroup: IModelGroup): void {
    this.editForm.patchValue({
      id: modelGroup.id,
      modelGroup: modelGroup.modelGroup,
      groupDesc: modelGroup.groupDesc,
      listPrice: modelGroup.listPrice,
      brand: modelGroup.brand,
      model: modelGroup.model,
      origCountry: modelGroup.origCountry,
      network: modelGroup.network,
      camera: modelGroup.camera,
      memCardSlot: modelGroup.memCardSlot,
      dataTransfer: modelGroup.dataTransfer,
      warranty: modelGroup.warranty,
      warrantyProvider: modelGroup.warrantyProvider,
      modelCate: modelGroup.modelCate,
      remarks: modelGroup.remarks,
      remarksEndDate: modelGroup.remarksEndDate,
      brandChi: modelGroup.brandChi,
      modelChi: modelGroup.modelChi,
      origCountryChi: modelGroup.origCountryChi,
      networkChi: modelGroup.networkChi,
      cameraChi: modelGroup.cameraChi,
      memCardSlotChi: modelGroup.memCardSlotChi,
      dataTransferChi: modelGroup.dataTransferChi,
      warrantyChi: modelGroup.warrantyChi,
      warrantyProviderChi: modelGroup.warrantyProviderChi,
      modelCateChi: modelGroup.modelCateChi,
      remarksChi: modelGroup.remarksChi,
      remarksChiEndDate: modelGroup.remarksChiEndDate,
      couponFlag: modelGroup.couponFlag,
      createdDate: modelGroup.createdDate ? modelGroup.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: modelGroup.createdBy,
      lastUpdatedDate: modelGroup.lastUpdatedDate ? modelGroup.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: modelGroup.lastUpdatedBy,
      tenantId: modelGroup.tenantId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const modelGroup = this.createFromForm();
    if (modelGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.modelGroupService.update(modelGroup));
    } else {
      this.subscribeToSaveResponse(this.modelGroupService.create(modelGroup));
    }
  }

  private createFromForm(): IModelGroup {
    return {
      ...new ModelGroup(),
      id: this.editForm.get(['id'])!.value,
      modelGroup: this.editForm.get(['modelGroup'])!.value,
      groupDesc: this.editForm.get(['groupDesc'])!.value,
      listPrice: this.editForm.get(['listPrice'])!.value,
      brand: this.editForm.get(['brand'])!.value,
      model: this.editForm.get(['model'])!.value,
      origCountry: this.editForm.get(['origCountry'])!.value,
      network: this.editForm.get(['network'])!.value,
      camera: this.editForm.get(['camera'])!.value,
      memCardSlot: this.editForm.get(['memCardSlot'])!.value,
      dataTransfer: this.editForm.get(['dataTransfer'])!.value,
      warranty: this.editForm.get(['warranty'])!.value,
      warrantyProvider: this.editForm.get(['warrantyProvider'])!.value,
      modelCate: this.editForm.get(['modelCate'])!.value,
      remarks: this.editForm.get(['remarks'])!.value,
      remarksEndDate: this.editForm.get(['remarksEndDate'])!.value,
      brandChi: this.editForm.get(['brandChi'])!.value,
      modelChi: this.editForm.get(['modelChi'])!.value,
      origCountryChi: this.editForm.get(['origCountryChi'])!.value,
      networkChi: this.editForm.get(['networkChi'])!.value,
      cameraChi: this.editForm.get(['cameraChi'])!.value,
      memCardSlotChi: this.editForm.get(['memCardSlotChi'])!.value,
      dataTransferChi: this.editForm.get(['dataTransferChi'])!.value,
      warrantyChi: this.editForm.get(['warrantyChi'])!.value,
      warrantyProviderChi: this.editForm.get(['warrantyProviderChi'])!.value,
      modelCateChi: this.editForm.get(['modelCateChi'])!.value,
      remarksChi: this.editForm.get(['remarksChi'])!.value,
      remarksChiEndDate: this.editForm.get(['remarksChiEndDate'])!.value,
      couponFlag: this.editForm.get(['couponFlag'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IModelGroup>>): void {
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
