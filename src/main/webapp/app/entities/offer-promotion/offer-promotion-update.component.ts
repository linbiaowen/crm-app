import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IOfferPromotion, OfferPromotion } from 'app/shared/model/offer-promotion.model';
import { OfferPromotionService } from './offer-promotion.service';
import { IOffer } from 'app/shared/model/offer.model';
import { OfferService } from 'app/entities/offer/offer.service';

@Component({
  selector: 'jhi-offer-promotion-update',
  templateUrl: './offer-promotion-update.component.html'
})
export class OfferPromotionUpdateComponent implements OnInit {
  isSaving = false;
  offers: IOffer[] = [];

  editForm = this.fb.group({
    id: [],
    promoCode: [null, [Validators.required]],
    offerId: [null, [Validators.required]],
    startDate: [],
    endDate: [],
    promoType: [],
    offerPrice: [],
    offerDiscount: [],
    freeDataOfferId: [],
    status: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]],
    offer: []
  });

  constructor(
    protected offerPromotionService: OfferPromotionService,
    protected offerService: OfferService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ offerPromotion }) => {
      if (!offerPromotion.id) {
        const today = moment().startOf('day');
        offerPromotion.startDate = today;
        offerPromotion.endDate = today;
        offerPromotion.createdDate = today;
        offerPromotion.lastUpdatedDate = today;
      }

      this.updateForm(offerPromotion);

      this.offerService.query().subscribe((res: HttpResponse<IOffer[]>) => (this.offers = res.body || []));
    });
  }

  updateForm(offerPromotion: IOfferPromotion): void {
    this.editForm.patchValue({
      id: offerPromotion.id,
      promoCode: offerPromotion.promoCode,
      offerId: offerPromotion.offerId,
      startDate: offerPromotion.startDate ? offerPromotion.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: offerPromotion.endDate ? offerPromotion.endDate.format(DATE_TIME_FORMAT) : null,
      promoType: offerPromotion.promoType,
      offerPrice: offerPromotion.offerPrice,
      offerDiscount: offerPromotion.offerDiscount,
      freeDataOfferId: offerPromotion.freeDataOfferId,
      status: offerPromotion.status,
      createdDate: offerPromotion.createdDate ? offerPromotion.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: offerPromotion.createdBy,
      lastUpdatedDate: offerPromotion.lastUpdatedDate ? offerPromotion.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: offerPromotion.lastUpdatedBy,
      tenantId: offerPromotion.tenantId,
      offer: offerPromotion.offer
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const offerPromotion = this.createFromForm();
    if (offerPromotion.id !== undefined) {
      this.subscribeToSaveResponse(this.offerPromotionService.update(offerPromotion));
    } else {
      this.subscribeToSaveResponse(this.offerPromotionService.create(offerPromotion));
    }
  }

  private createFromForm(): IOfferPromotion {
    return {
      ...new OfferPromotion(),
      id: this.editForm.get(['id'])!.value,
      promoCode: this.editForm.get(['promoCode'])!.value,
      offerId: this.editForm.get(['offerId'])!.value,
      startDate: this.editForm.get(['startDate'])!.value ? moment(this.editForm.get(['startDate'])!.value, DATE_TIME_FORMAT) : undefined,
      endDate: this.editForm.get(['endDate'])!.value ? moment(this.editForm.get(['endDate'])!.value, DATE_TIME_FORMAT) : undefined,
      promoType: this.editForm.get(['promoType'])!.value,
      offerPrice: this.editForm.get(['offerPrice'])!.value,
      offerDiscount: this.editForm.get(['offerDiscount'])!.value,
      freeDataOfferId: this.editForm.get(['freeDataOfferId'])!.value,
      status: this.editForm.get(['status'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOfferPromotion>>): void {
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
