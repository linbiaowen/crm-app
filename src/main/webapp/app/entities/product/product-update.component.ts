import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IProduct, Product } from 'app/shared/model/product.model';
import { ProductService } from './product.service';
import { IProductSpecification } from 'app/shared/model/product-specification.model';
import { ProductSpecificationService } from 'app/entities/product-specification/product-specification.service';

@Component({
  selector: 'jhi-product-update',
  templateUrl: './product-update.component.html'
})
export class ProductUpdateComponent implements OnInit {
  isSaving = false;
  productspecs: IProductSpecification[] = [];

  editForm = this.fb.group({
    id: [],
    productId: [null, [Validators.required]],
    productName: [null, [Validators.required]],
    productNameChi: [],
    productDesc: [],
    productDescChi: [],
    productCate: [],
    productNature: [],
    productFamily: [],
    productType: [],
    modelCode: [],
    startDate: [],
    endDate: [],
    independentlyOrderable: [],
    networkProvisionRequired: [],
    changeEntitlementAllowed: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]],
    productSpec: []
  });

  constructor(
    protected productService: ProductService,
    protected productSpecificationService: ProductSpecificationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ product }) => {
      if (!product.id) {
        const today = moment().startOf('day');
        product.startDate = today;
        product.endDate = today;
        product.createdDate = today;
        product.lastUpdatedDate = today;
      }

      this.updateForm(product);

      this.productSpecificationService
        .query({ filter: 'product-is-null' })
        .pipe(
          map((res: HttpResponse<IProductSpecification[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IProductSpecification[]) => {
          if (!product.productSpec || !product.productSpec.id) {
            this.productspecs = resBody;
          } else {
            this.productSpecificationService
              .find(product.productSpec.id)
              .pipe(
                map((subRes: HttpResponse<IProductSpecification>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IProductSpecification[]) => (this.productspecs = concatRes));
          }
        });
    });
  }

  updateForm(product: IProduct): void {
    this.editForm.patchValue({
      id: product.id,
      productId: product.productId,
      productName: product.productName,
      productNameChi: product.productNameChi,
      productDesc: product.productDesc,
      productDescChi: product.productDescChi,
      productCate: product.productCate,
      productNature: product.productNature,
      productFamily: product.productFamily,
      productType: product.productType,
      modelCode: product.modelCode,
      startDate: product.startDate ? product.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: product.endDate ? product.endDate.format(DATE_TIME_FORMAT) : null,
      independentlyOrderable: product.independentlyOrderable,
      networkProvisionRequired: product.networkProvisionRequired,
      changeEntitlementAllowed: product.changeEntitlementAllowed,
      createdDate: product.createdDate ? product.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: product.createdBy,
      lastUpdatedDate: product.lastUpdatedDate ? product.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: product.lastUpdatedBy,
      tenantId: product.tenantId,
      productSpec: product.productSpec
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const product = this.createFromForm();
    if (product.id !== undefined) {
      this.subscribeToSaveResponse(this.productService.update(product));
    } else {
      this.subscribeToSaveResponse(this.productService.create(product));
    }
  }

  private createFromForm(): IProduct {
    return {
      ...new Product(),
      id: this.editForm.get(['id'])!.value,
      productId: this.editForm.get(['productId'])!.value,
      productName: this.editForm.get(['productName'])!.value,
      productNameChi: this.editForm.get(['productNameChi'])!.value,
      productDesc: this.editForm.get(['productDesc'])!.value,
      productDescChi: this.editForm.get(['productDescChi'])!.value,
      productCate: this.editForm.get(['productCate'])!.value,
      productNature: this.editForm.get(['productNature'])!.value,
      productFamily: this.editForm.get(['productFamily'])!.value,
      productType: this.editForm.get(['productType'])!.value,
      modelCode: this.editForm.get(['modelCode'])!.value,
      startDate: this.editForm.get(['startDate'])!.value ? moment(this.editForm.get(['startDate'])!.value, DATE_TIME_FORMAT) : undefined,
      endDate: this.editForm.get(['endDate'])!.value ? moment(this.editForm.get(['endDate'])!.value, DATE_TIME_FORMAT) : undefined,
      independentlyOrderable: this.editForm.get(['independentlyOrderable'])!.value,
      networkProvisionRequired: this.editForm.get(['networkProvisionRequired'])!.value,
      changeEntitlementAllowed: this.editForm.get(['changeEntitlementAllowed'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      lastUpdatedDate: this.editForm.get(['lastUpdatedDate'])!.value
        ? moment(this.editForm.get(['lastUpdatedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastUpdatedBy: this.editForm.get(['lastUpdatedBy'])!.value,
      tenantId: this.editForm.get(['tenantId'])!.value,
      productSpec: this.editForm.get(['productSpec'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProduct>>): void {
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

  trackById(index: number, item: IProductSpecification): any {
    return item.id;
  }
}
