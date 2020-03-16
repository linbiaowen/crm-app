import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IOfferAdvancePayment, OfferAdvancePayment } from 'app/shared/model/offer-advance-payment.model';
import { OfferAdvancePaymentService } from './offer-advance-payment.service';
import { IOffer } from 'app/shared/model/offer.model';
import { OfferService } from 'app/entities/offer/offer.service';

@Component({
  selector: 'jhi-offer-advance-payment-update',
  templateUrl: './offer-advance-payment-update.component.html'
})
export class OfferAdvancePaymentUpdateComponent implements OnInit {
  isSaving = false;
  offers: IOffer[] = [];

  editForm = this.fb.group({
    id: [],
    advancePaymentId: [null, [Validators.required]],
    offerId: [null, [Validators.required]],
    advancePaymentMonths: [null, [Validators.required]],
    offerPrice: [],
    offerDiscount: [],
    status: [null, [Validators.required]],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]],
    offer: []
  });

  constructor(
    protected offerAdvancePaymentService: OfferAdvancePaymentService,
    protected offerService: OfferService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ offerAdvancePayment }) => {
      if (!offerAdvancePayment.id) {
        const today = moment().startOf('day');
        offerAdvancePayment.createdDate = today;
        offerAdvancePayment.lastUpdatedDate = today;
      }

      this.updateForm(offerAdvancePayment);

      this.offerService.query().subscribe((res: HttpResponse<IOffer[]>) => (this.offers = res.body || []));
    });
  }

  updateForm(offerAdvancePayment: IOfferAdvancePayment): void {
    this.editForm.patchValue({
      id: offerAdvancePayment.id,
      advancePaymentId: offerAdvancePayment.advancePaymentId,
      offerId: offerAdvancePayment.offerId,
      advancePaymentMonths: offerAdvancePayment.advancePaymentMonths,
      offerPrice: offerAdvancePayment.offerPrice,
      offerDiscount: offerAdvancePayment.offerDiscount,
      status: offerAdvancePayment.status,
      createdDate: offerAdvancePayment.createdDate ? offerAdvancePayment.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: offerAdvancePayment.createdBy,
      lastUpdatedDate: offerAdvancePayment.lastUpdatedDate ? offerAdvancePayment.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: offerAdvancePayment.lastUpdatedBy,
      tenantId: offerAdvancePayment.tenantId,
      offer: offerAdvancePayment.offer
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const offerAdvancePayment = this.createFromForm();
    if (offerAdvancePayment.id !== undefined) {
      this.subscribeToSaveResponse(this.offerAdvancePaymentService.update(offerAdvancePayment));
    } else {
      this.subscribeToSaveResponse(this.offerAdvancePaymentService.create(offerAdvancePayment));
    }
  }

  private createFromForm(): IOfferAdvancePayment {
    return {
      ...new OfferAdvancePayment(),
      id: this.editForm.get(['id'])!.value,
      advancePaymentId: this.editForm.get(['advancePaymentId'])!.value,
      offerId: this.editForm.get(['offerId'])!.value,
      advancePaymentMonths: this.editForm.get(['advancePaymentMonths'])!.value,
      offerPrice: this.editForm.get(['offerPrice'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOfferAdvancePayment>>): void {
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
