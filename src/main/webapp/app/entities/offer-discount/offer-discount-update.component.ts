import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IOfferDiscount, OfferDiscount } from 'app/shared/model/offer-discount.model';
import { OfferDiscountService } from './offer-discount.service';
import { IOffer } from 'app/shared/model/offer.model';
import { OfferService } from 'app/entities/offer/offer.service';

@Component({
  selector: 'jhi-offer-discount-update',
  templateUrl: './offer-discount-update.component.html'
})
export class OfferDiscountUpdateComponent implements OnInit {
  isSaving = false;
  offers: IOffer[] = [];

  editForm = this.fb.group({
    id: [],
    discountCode: [null, [Validators.required]],
    offerId: [],
    startDate: [],
    endDate: [],
    discountType: [],
    offerDiscount: [],
    status: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]],
    offer: []
  });

  constructor(
    protected offerDiscountService: OfferDiscountService,
    protected offerService: OfferService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ offerDiscount }) => {
      if (!offerDiscount.id) {
        const today = moment().startOf('day');
        offerDiscount.startDate = today;
        offerDiscount.endDate = today;
        offerDiscount.createdDate = today;
        offerDiscount.lastUpdatedDate = today;
      }

      this.updateForm(offerDiscount);

      this.offerService.query().subscribe((res: HttpResponse<IOffer[]>) => (this.offers = res.body || []));
    });
  }

  updateForm(offerDiscount: IOfferDiscount): void {
    this.editForm.patchValue({
      id: offerDiscount.id,
      discountCode: offerDiscount.discountCode,
      offerId: offerDiscount.offerId,
      startDate: offerDiscount.startDate ? offerDiscount.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: offerDiscount.endDate ? offerDiscount.endDate.format(DATE_TIME_FORMAT) : null,
      discountType: offerDiscount.discountType,
      offerDiscount: offerDiscount.offerDiscount,
      status: offerDiscount.status,
      createdDate: offerDiscount.createdDate ? offerDiscount.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: offerDiscount.createdBy,
      lastUpdatedDate: offerDiscount.lastUpdatedDate ? offerDiscount.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: offerDiscount.lastUpdatedBy,
      tenantId: offerDiscount.tenantId,
      offer: offerDiscount.offer
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const offerDiscount = this.createFromForm();
    if (offerDiscount.id !== undefined) {
      this.subscribeToSaveResponse(this.offerDiscountService.update(offerDiscount));
    } else {
      this.subscribeToSaveResponse(this.offerDiscountService.create(offerDiscount));
    }
  }

  private createFromForm(): IOfferDiscount {
    return {
      ...new OfferDiscount(),
      id: this.editForm.get(['id'])!.value,
      discountCode: this.editForm.get(['discountCode'])!.value,
      offerId: this.editForm.get(['offerId'])!.value,
      startDate: this.editForm.get(['startDate'])!.value ? moment(this.editForm.get(['startDate'])!.value, DATE_TIME_FORMAT) : undefined,
      endDate: this.editForm.get(['endDate'])!.value ? moment(this.editForm.get(['endDate'])!.value, DATE_TIME_FORMAT) : undefined,
      discountType: this.editForm.get(['discountType'])!.value,
      offerDiscount: this.editForm.get(['offerDiscount'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOfferDiscount>>): void {
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
