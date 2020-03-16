import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IOfferProduct, OfferProduct } from 'app/shared/model/offer-product.model';
import { OfferProductService } from './offer-product.service';
import { IOffer } from 'app/shared/model/offer.model';
import { OfferService } from 'app/entities/offer/offer.service';

@Component({
  selector: 'jhi-offer-product-update',
  templateUrl: './offer-product-update.component.html'
})
export class OfferProductUpdateComponent implements OnInit {
  isSaving = false;
  offers: IOffer[] = [];

  editForm = this.fb.group({
    id: [],
    recSeqId: [],
    productId: [],
    offerId: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]],
    offer: []
  });

  constructor(
    protected offerProductService: OfferProductService,
    protected offerService: OfferService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ offerProduct }) => {
      if (!offerProduct.id) {
        const today = moment().startOf('day');
        offerProduct.createdDate = today;
        offerProduct.lastUpdatedDate = today;
      }

      this.updateForm(offerProduct);

      this.offerService.query().subscribe((res: HttpResponse<IOffer[]>) => (this.offers = res.body || []));
    });
  }

  updateForm(offerProduct: IOfferProduct): void {
    this.editForm.patchValue({
      id: offerProduct.id,
      recSeqId: offerProduct.recSeqId,
      productId: offerProduct.productId,
      offerId: offerProduct.offerId,
      createdDate: offerProduct.createdDate ? offerProduct.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: offerProduct.createdBy,
      lastUpdatedDate: offerProduct.lastUpdatedDate ? offerProduct.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: offerProduct.lastUpdatedBy,
      tenantId: offerProduct.tenantId,
      offer: offerProduct.offer
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const offerProduct = this.createFromForm();
    if (offerProduct.id !== undefined) {
      this.subscribeToSaveResponse(this.offerProductService.update(offerProduct));
    } else {
      this.subscribeToSaveResponse(this.offerProductService.create(offerProduct));
    }
  }

  private createFromForm(): IOfferProduct {
    return {
      ...new OfferProduct(),
      id: this.editForm.get(['id'])!.value,
      recSeqId: this.editForm.get(['recSeqId'])!.value,
      productId: this.editForm.get(['productId'])!.value,
      offerId: this.editForm.get(['offerId'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      lastUpdatedDate: this.editForm.get(['lastUpdatedDate'])!.value
        ? moment(this.editForm.get(['lastUpdatedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastUpdatedBy: this.editForm.get(['lastUpdatedBy'])!.value,
      tenantId: this.editForm.get(['tenantId'])!.value,
      offer: this.editForm.get(['offer'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOfferProduct>>): void {
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

  trackById(index: number, item: IOffer): any {
    return item.id;
  }
}
