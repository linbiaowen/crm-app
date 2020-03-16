import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IOffer, Offer } from 'app/shared/model/offer.model';
import { OfferService } from './offer.service';
import { IOfferSpecification } from 'app/shared/model/offer-specification.model';
import { OfferSpecificationService } from 'app/entities/offer-specification/offer-specification.service';

type SelectableEntity = IOfferSpecification | IOffer;

@Component({
  selector: 'jhi-offer-update',
  templateUrl: './offer-update.component.html'
})
export class OfferUpdateComponent implements OnInit {
  isSaving = false;
  offerspecs: IOfferSpecification[] = [];
  offers: IOffer[] = [];

  editForm = this.fb.group({
    id: [],
    offerId: [null, [Validators.required]],
    offerExternalId: [],
    offerName: [null, [Validators.required]],
    offerNameChi: [],
    offerType: [],
    offerPrice: [],
    customerSegments: [],
    customerClasses: [],
    salesChannels: [],
    startDate: [],
    endDate: [],
    childOfferIds: [],
    productSpecIds: [],
    advancePaymentIds: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]],
    offerSpec: [],
    parentOffers: []
  });

  constructor(
    protected offerService: OfferService,
    protected offerSpecificationService: OfferSpecificationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ offer }) => {
      if (!offer.id) {
        const today = moment().startOf('day');
        offer.startDate = today;
        offer.endDate = today;
        offer.createdDate = today;
        offer.lastUpdatedDate = today;
      }

      this.updateForm(offer);

      this.offerSpecificationService
        .query({ filter: 'offer-is-null' })
        .pipe(
          map((res: HttpResponse<IOfferSpecification[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IOfferSpecification[]) => {
          if (!offer.offerSpec || !offer.offerSpec.id) {
            this.offerspecs = resBody;
          } else {
            this.offerSpecificationService
              .find(offer.offerSpec.id)
              .pipe(
                map((subRes: HttpResponse<IOfferSpecification>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IOfferSpecification[]) => (this.offerspecs = concatRes));
          }
        });

      this.offerService.query().subscribe((res: HttpResponse<IOffer[]>) => (this.offers = res.body || []));
    });
  }

  updateForm(offer: IOffer): void {
    this.editForm.patchValue({
      id: offer.id,
      offerId: offer.offerId,
      offerExternalId: offer.offerExternalId,
      offerName: offer.offerName,
      offerNameChi: offer.offerNameChi,
      offerType: offer.offerType,
      offerPrice: offer.offerPrice,
      customerSegments: offer.customerSegments,
      customerClasses: offer.customerClasses,
      salesChannels: offer.salesChannels,
      startDate: offer.startDate ? offer.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: offer.endDate ? offer.endDate.format(DATE_TIME_FORMAT) : null,
      childOfferIds: offer.childOfferIds,
      productSpecIds: offer.productSpecIds,
      advancePaymentIds: offer.advancePaymentIds,
      createdDate: offer.createdDate ? offer.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: offer.createdBy,
      lastUpdatedDate: offer.lastUpdatedDate ? offer.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: offer.lastUpdatedBy,
      tenantId: offer.tenantId,
      offerSpec: offer.offerSpec,
      parentOffers: offer.parentOffers
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const offer = this.createFromForm();
    if (offer.id !== undefined) {
      this.subscribeToSaveResponse(this.offerService.update(offer));
    } else {
      this.subscribeToSaveResponse(this.offerService.create(offer));
    }
  }

  private createFromForm(): IOffer {
    return {
      ...new Offer(),
      id: this.editForm.get(['id'])!.value,
      offerId: this.editForm.get(['offerId'])!.value,
      offerExternalId: this.editForm.get(['offerExternalId'])!.value,
      offerName: this.editForm.get(['offerName'])!.value,
      offerNameChi: this.editForm.get(['offerNameChi'])!.value,
      offerType: this.editForm.get(['offerType'])!.value,
      offerPrice: this.editForm.get(['offerPrice'])!.value,
      customerSegments: this.editForm.get(['customerSegments'])!.value,
      customerClasses: this.editForm.get(['customerClasses'])!.value,
      salesChannels: this.editForm.get(['salesChannels'])!.value,
      startDate: this.editForm.get(['startDate'])!.value ? moment(this.editForm.get(['startDate'])!.value, DATE_TIME_FORMAT) : undefined,
      endDate: this.editForm.get(['endDate'])!.value ? moment(this.editForm.get(['endDate'])!.value, DATE_TIME_FORMAT) : undefined,
      childOfferIds: this.editForm.get(['childOfferIds'])!.value,
      productSpecIds: this.editForm.get(['productSpecIds'])!.value,
      advancePaymentIds: this.editForm.get(['advancePaymentIds'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      lastUpdatedDate: this.editForm.get(['lastUpdatedDate'])!.value
        ? moment(this.editForm.get(['lastUpdatedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastUpdatedBy: this.editForm.get(['lastUpdatedBy'])!.value,
      tenantId: this.editForm.get(['tenantId'])!.value,
      offerSpec: this.editForm.get(['offerSpec'])!.value,
      parentOffers: this.editForm.get(['parentOffers'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOffer>>): void {
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

  getSelected(selectedVals: IOffer[], option: IOffer): IOffer {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
