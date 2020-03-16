import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IResourceSpecification, ResourceSpecification } from 'app/shared/model/resource-specification.model';
import { ResourceSpecificationService } from './resource-specification.service';
import { ICfsServiceSpec } from 'app/shared/model/cfs-service-spec.model';
import { CfsServiceSpecService } from 'app/entities/cfs-service-spec/cfs-service-spec.service';

@Component({
  selector: 'jhi-resource-specification-update',
  templateUrl: './resource-specification-update.component.html'
})
export class ResourceSpecificationUpdateComponent implements OnInit {
  isSaving = false;
  cfsservicespecs: ICfsServiceSpec[] = [];

  editForm = this.fb.group({
    id: [],
    resourceSpecId: [null, [Validators.required]],
    resourceType: [null, [Validators.required]],
    serviceSpecId: [],
    rfs: [null, [Validators.required]],
    rfsParms: [],
    remarks: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]],
    cfsServiceSpec: []
  });

  constructor(
    protected resourceSpecificationService: ResourceSpecificationService,
    protected cfsServiceSpecService: CfsServiceSpecService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ resourceSpecification }) => {
      if (!resourceSpecification.id) {
        const today = moment().startOf('day');
        resourceSpecification.createdDate = today;
        resourceSpecification.lastUpdatedDate = today;
      }

      this.updateForm(resourceSpecification);

      this.cfsServiceSpecService.query().subscribe((res: HttpResponse<ICfsServiceSpec[]>) => (this.cfsservicespecs = res.body || []));
    });
  }

  updateForm(resourceSpecification: IResourceSpecification): void {
    this.editForm.patchValue({
      id: resourceSpecification.id,
      resourceSpecId: resourceSpecification.resourceSpecId,
      resourceType: resourceSpecification.resourceType,
      serviceSpecId: resourceSpecification.serviceSpecId,
      rfs: resourceSpecification.rfs,
      rfsParms: resourceSpecification.rfsParms,
      remarks: resourceSpecification.remarks,
      createdDate: resourceSpecification.createdDate ? resourceSpecification.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: resourceSpecification.createdBy,
      lastUpdatedDate: resourceSpecification.lastUpdatedDate ? resourceSpecification.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: resourceSpecification.lastUpdatedBy,
      tenantId: resourceSpecification.tenantId,
      cfsServiceSpec: resourceSpecification.cfsServiceSpec
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const resourceSpecification = this.createFromForm();
    if (resourceSpecification.id !== undefined) {
      this.subscribeToSaveResponse(this.resourceSpecificationService.update(resourceSpecification));
    } else {
      this.subscribeToSaveResponse(this.resourceSpecificationService.create(resourceSpecification));
    }
  }

  private createFromForm(): IResourceSpecification {
    return {
      ...new ResourceSpecification(),
      id: this.editForm.get(['id'])!.value,
      resourceSpecId: this.editForm.get(['resourceSpecId'])!.value,
      resourceType: this.editForm.get(['resourceType'])!.value,
      serviceSpecId: this.editForm.get(['serviceSpecId'])!.value,
      rfs: this.editForm.get(['rfs'])!.value,
      rfsParms: this.editForm.get(['rfsParms'])!.value,
      remarks: this.editForm.get(['remarks'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      lastUpdatedDate: this.editForm.get(['lastUpdatedDate'])!.value
        ? moment(this.editForm.get(['lastUpdatedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastUpdatedBy: this.editForm.get(['lastUpdatedBy'])!.value,
      tenantId: this.editForm.get(['tenantId'])!.value,
      cfsServiceSpec: this.editForm.get(['cfsServiceSpec'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IResourceSpecification>>): void {
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

  trackById(index: number, item: ICfsServiceSpec): any {
    return item.id;
  }
}
