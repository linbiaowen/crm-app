import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICfsServiceSpec, CfsServiceSpec } from 'app/shared/model/cfs-service-spec.model';
import { CfsServiceSpecService } from './cfs-service-spec.service';
import { IVoiceServiceSpec } from 'app/shared/model/voice-service-spec.model';
import { VoiceServiceSpecService } from 'app/entities/voice-service-spec/voice-service-spec.service';
import { IDataServiceSpec } from 'app/shared/model/data-service-spec.model';
import { DataServiceSpecService } from 'app/entities/data-service-spec/data-service-spec.service';
import { IProductSpecification } from 'app/shared/model/product-specification.model';
import { ProductSpecificationService } from 'app/entities/product-specification/product-specification.service';

type SelectableEntity = IVoiceServiceSpec | IDataServiceSpec | IProductSpecification;

@Component({
  selector: 'jhi-cfs-service-spec-update',
  templateUrl: './cfs-service-spec-update.component.html'
})
export class CfsServiceSpecUpdateComponent implements OnInit {
  isSaving = false;
  voiceservicespecs: IVoiceServiceSpec[] = [];
  dataservicespecs: IDataServiceSpec[] = [];
  productspecifications: IProductSpecification[] = [];

  editForm = this.fb.group({
    id: [],
    serviceSpecId: [null, [Validators.required]],
    serviceSpecDesc: [],
    serviceId: [null, [Validators.required]],
    voiceSpecId: [],
    dataSpecId: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]],
    voiceServiceSpec: [],
    dataServiceSpec: [],
    productSpecification: []
  });

  constructor(
    protected cfsServiceSpecService: CfsServiceSpecService,
    protected voiceServiceSpecService: VoiceServiceSpecService,
    protected dataServiceSpecService: DataServiceSpecService,
    protected productSpecificationService: ProductSpecificationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cfsServiceSpec }) => {
      if (!cfsServiceSpec.id) {
        const today = moment().startOf('day');
        cfsServiceSpec.createdDate = today;
        cfsServiceSpec.lastUpdatedDate = today;
      }

      this.updateForm(cfsServiceSpec);

      this.voiceServiceSpecService
        .query({ filter: 'cfsservices-is-null' })
        .pipe(
          map((res: HttpResponse<IVoiceServiceSpec[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IVoiceServiceSpec[]) => {
          if (!cfsServiceSpec.voiceServiceSpec || !cfsServiceSpec.voiceServiceSpec.id) {
            this.voiceservicespecs = resBody;
          } else {
            this.voiceServiceSpecService
              .find(cfsServiceSpec.voiceServiceSpec.id)
              .pipe(
                map((subRes: HttpResponse<IVoiceServiceSpec>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IVoiceServiceSpec[]) => (this.voiceservicespecs = concatRes));
          }
        });

      this.dataServiceSpecService
        .query({ filter: 'cfsservices-is-null' })
        .pipe(
          map((res: HttpResponse<IDataServiceSpec[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IDataServiceSpec[]) => {
          if (!cfsServiceSpec.dataServiceSpec || !cfsServiceSpec.dataServiceSpec.id) {
            this.dataservicespecs = resBody;
          } else {
            this.dataServiceSpecService
              .find(cfsServiceSpec.dataServiceSpec.id)
              .pipe(
                map((subRes: HttpResponse<IDataServiceSpec>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IDataServiceSpec[]) => (this.dataservicespecs = concatRes));
          }
        });

      this.productSpecificationService
        .query()
        .subscribe((res: HttpResponse<IProductSpecification[]>) => (this.productspecifications = res.body || []));
    });
  }

  updateForm(cfsServiceSpec: ICfsServiceSpec): void {
    this.editForm.patchValue({
      id: cfsServiceSpec.id,
      serviceSpecId: cfsServiceSpec.serviceSpecId,
      serviceSpecDesc: cfsServiceSpec.serviceSpecDesc,
      serviceId: cfsServiceSpec.serviceId,
      voiceSpecId: cfsServiceSpec.voiceSpecId,
      dataSpecId: cfsServiceSpec.dataSpecId,
      createdDate: cfsServiceSpec.createdDate ? cfsServiceSpec.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: cfsServiceSpec.createdBy,
      lastUpdatedDate: cfsServiceSpec.lastUpdatedDate ? cfsServiceSpec.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: cfsServiceSpec.lastUpdatedBy,
      tenantId: cfsServiceSpec.tenantId,
      voiceServiceSpec: cfsServiceSpec.voiceServiceSpec,
      dataServiceSpec: cfsServiceSpec.dataServiceSpec,
      productSpecification: cfsServiceSpec.productSpecification
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cfsServiceSpec = this.createFromForm();
    if (cfsServiceSpec.id !== undefined) {
      this.subscribeToSaveResponse(this.cfsServiceSpecService.update(cfsServiceSpec));
    } else {
      this.subscribeToSaveResponse(this.cfsServiceSpecService.create(cfsServiceSpec));
    }
  }

  private createFromForm(): ICfsServiceSpec {
    return {
      ...new CfsServiceSpec(),
      id: this.editForm.get(['id'])!.value,
      serviceSpecId: this.editForm.get(['serviceSpecId'])!.value,
      serviceSpecDesc: this.editForm.get(['serviceSpecDesc'])!.value,
      serviceId: this.editForm.get(['serviceId'])!.value,
      voiceSpecId: this.editForm.get(['voiceSpecId'])!.value,
      dataSpecId: this.editForm.get(['dataSpecId'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      lastUpdatedDate: this.editForm.get(['lastUpdatedDate'])!.value
        ? moment(this.editForm.get(['lastUpdatedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastUpdatedBy: this.editForm.get(['lastUpdatedBy'])!.value,
      tenantId: this.editForm.get(['tenantId'])!.value,
      voiceServiceSpec: this.editForm.get(['voiceServiceSpec'])!.value,
      dataServiceSpec: this.editForm.get(['dataServiceSpec'])!.value,
      productSpecification: this.editForm.get(['productSpecification'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICfsServiceSpec>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
