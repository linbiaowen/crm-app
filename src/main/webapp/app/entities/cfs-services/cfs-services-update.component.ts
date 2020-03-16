import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICfsServices, CfsServices } from 'app/shared/model/cfs-services.model';
import { CfsServicesService } from './cfs-services.service';
import { ICfsServiceSpec } from 'app/shared/model/cfs-service-spec.model';
import { CfsServiceSpecService } from 'app/entities/cfs-service-spec/cfs-service-spec.service';

@Component({
  selector: 'jhi-cfs-services-update',
  templateUrl: './cfs-services-update.component.html'
})
export class CfsServicesUpdateComponent implements OnInit {
  isSaving = false;
  cfsservicespecs: ICfsServiceSpec[] = [];

  editForm = this.fb.group({
    id: [],
    serviceId: [null, [Validators.required]],
    serviceName: [null, [Validators.required]],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]],
    cfsServiceSpec: []
  });

  constructor(
    protected cfsServicesService: CfsServicesService,
    protected cfsServiceSpecService: CfsServiceSpecService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cfsServices }) => {
      if (!cfsServices.id) {
        const today = moment().startOf('day');
        cfsServices.createdDate = today;
        cfsServices.lastUpdatedDate = today;
      }

      this.updateForm(cfsServices);

      this.cfsServiceSpecService
        .query({ filter: 'cfsservices-is-null' })
        .pipe(
          map((res: HttpResponse<ICfsServiceSpec[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ICfsServiceSpec[]) => {
          if (!cfsServices.cfsServiceSpec || !cfsServices.cfsServiceSpec.id) {
            this.cfsservicespecs = resBody;
          } else {
            this.cfsServiceSpecService
              .find(cfsServices.cfsServiceSpec.id)
              .pipe(
                map((subRes: HttpResponse<ICfsServiceSpec>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ICfsServiceSpec[]) => (this.cfsservicespecs = concatRes));
          }
        });
    });
  }

  updateForm(cfsServices: ICfsServices): void {
    this.editForm.patchValue({
      id: cfsServices.id,
      serviceId: cfsServices.serviceId,
      serviceName: cfsServices.serviceName,
      createdDate: cfsServices.createdDate ? cfsServices.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: cfsServices.createdBy,
      lastUpdatedDate: cfsServices.lastUpdatedDate ? cfsServices.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: cfsServices.lastUpdatedBy,
      tenantId: cfsServices.tenantId,
      cfsServiceSpec: cfsServices.cfsServiceSpec
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cfsServices = this.createFromForm();
    if (cfsServices.id !== undefined) {
      this.subscribeToSaveResponse(this.cfsServicesService.update(cfsServices));
    } else {
      this.subscribeToSaveResponse(this.cfsServicesService.create(cfsServices));
    }
  }

  private createFromForm(): ICfsServices {
    return {
      ...new CfsServices(),
      id: this.editForm.get(['id'])!.value,
      serviceId: this.editForm.get(['serviceId'])!.value,
      serviceName: this.editForm.get(['serviceName'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICfsServices>>): void {
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
