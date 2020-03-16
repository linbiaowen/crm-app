import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IProductSpecification, ProductSpecification } from 'app/shared/model/product-specification.model';
import { ProductSpecificationService } from './product-specification.service';
import { IProductVoice } from 'app/shared/model/product-voice.model';
import { ProductVoiceService } from 'app/entities/product-voice/product-voice.service';
import { IProductData } from 'app/shared/model/product-data.model';
import { ProductDataService } from 'app/entities/product-data/product-data.service';
import { IProductSms } from 'app/shared/model/product-sms.model';
import { ProductSmsService } from 'app/entities/product-sms/product-sms.service';
import { IOffer } from 'app/shared/model/offer.model';
import { OfferService } from 'app/entities/offer/offer.service';

type SelectableEntity = IProductVoice | IProductData | IProductSms | IOffer;

@Component({
  selector: 'jhi-product-specification-update',
  templateUrl: './product-specification-update.component.html'
})
export class ProductSpecificationUpdateComponent implements OnInit {
  isSaving = false;
  voices: IProductVoice[] = [];
  data: IProductData[] = [];
  sms: IProductSms[] = [];
  offers: IOffer[] = [];

  editForm = this.fb.group({
    id: [],
    productSpecId: [null, [Validators.required]],
    productId: [null, [Validators.required]],
    serviceSpecId: [],
    productSpecType: [],
    skuType: [],
    simType: [],
    boxType: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]],
    voice: [],
    data: [],
    sms: [],
    offer: []
  });

  constructor(
    protected productSpecificationService: ProductSpecificationService,
    protected productVoiceService: ProductVoiceService,
    protected productDataService: ProductDataService,
    protected productSmsService: ProductSmsService,
    protected offerService: OfferService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productSpecification }) => {
      if (!productSpecification.id) {
        const today = moment().startOf('day');
        productSpecification.createdDate = today;
        productSpecification.lastUpdatedDate = today;
      }

      this.updateForm(productSpecification);

      this.productVoiceService
        .query({ filter: 'productspec-is-null' })
        .pipe(
          map((res: HttpResponse<IProductVoice[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IProductVoice[]) => {
          if (!productSpecification.voice || !productSpecification.voice.id) {
            this.voices = resBody;
          } else {
            this.productVoiceService
              .find(productSpecification.voice.id)
              .pipe(
                map((subRes: HttpResponse<IProductVoice>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IProductVoice[]) => (this.voices = concatRes));
          }
        });

      this.productDataService
        .query({ filter: 'productspec-is-null' })
        .pipe(
          map((res: HttpResponse<IProductData[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IProductData[]) => {
          if (!productSpecification.data || !productSpecification.data.id) {
            this.data = resBody;
          } else {
            this.productDataService
              .find(productSpecification.data.id)
              .pipe(
                map((subRes: HttpResponse<IProductData>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IProductData[]) => (this.data = concatRes));
          }
        });

      this.productSmsService
        .query({ filter: 'productspec-is-null' })
        .pipe(
          map((res: HttpResponse<IProductSms[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IProductSms[]) => {
          if (!productSpecification.sms || !productSpecification.sms.id) {
            this.sms = resBody;
          } else {
            this.productSmsService
              .find(productSpecification.sms.id)
              .pipe(
                map((subRes: HttpResponse<IProductSms>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IProductSms[]) => (this.sms = concatRes));
          }
        });

      this.offerService.query().subscribe((res: HttpResponse<IOffer[]>) => (this.offers = res.body || []));
    });
  }

  updateForm(productSpecification: IProductSpecification): void {
    this.editForm.patchValue({
      id: productSpecification.id,
      productSpecId: productSpecification.productSpecId,
      productId: productSpecification.productId,
      serviceSpecId: productSpecification.serviceSpecId,
      productSpecType: productSpecification.productSpecType,
      skuType: productSpecification.skuType,
      simType: productSpecification.simType,
      boxType: productSpecification.boxType,
      createdDate: productSpecification.createdDate ? productSpecification.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: productSpecification.createdBy,
      lastUpdatedDate: productSpecification.lastUpdatedDate ? productSpecification.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: productSpecification.lastUpdatedBy,
      tenantId: productSpecification.tenantId,
      voice: productSpecification.voice,
      data: productSpecification.data,
      sms: productSpecification.sms,
      offer: productSpecification.offer
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const productSpecification = this.createFromForm();
    if (productSpecification.id !== undefined) {
      this.subscribeToSaveResponse(this.productSpecificationService.update(productSpecification));
    } else {
      this.subscribeToSaveResponse(this.productSpecificationService.create(productSpecification));
    }
  }

  private createFromForm(): IProductSpecification {
    return {
      ...new ProductSpecification(),
      id: this.editForm.get(['id'])!.value,
      productSpecId: this.editForm.get(['productSpecId'])!.value,
      productId: this.editForm.get(['productId'])!.value,
      serviceSpecId: this.editForm.get(['serviceSpecId'])!.value,
      productSpecType: this.editForm.get(['productSpecType'])!.value,
      skuType: this.editForm.get(['skuType'])!.value,
      simType: this.editForm.get(['simType'])!.value,
      boxType: this.editForm.get(['boxType'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      lastUpdatedDate: this.editForm.get(['lastUpdatedDate'])!.value
        ? moment(this.editForm.get(['lastUpdatedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastUpdatedBy: this.editForm.get(['lastUpdatedBy'])!.value,
      tenantId: this.editForm.get(['tenantId'])!.value,
      voice: this.editForm.get(['voice'])!.value,
      data: this.editForm.get(['data'])!.value,
      sms: this.editForm.get(['sms'])!.value,
      offer: this.editForm.get(['offer'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProductSpecification>>): void {
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
