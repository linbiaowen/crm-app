import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IOfferSpecification, OfferSpecification } from 'app/shared/model/offer-specification.model';
import { OfferSpecificationService } from './offer-specification.service';

@Component({
  selector: 'jhi-offer-specification-update',
  templateUrl: './offer-specification-update.component.html'
})
export class OfferSpecificationUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    offerSpecId: [null, [Validators.required]],
    offerId: [null, [Validators.required]],
    startDate: [],
    endDate: [],
    limitedActivationPeriod: [],
    allowedActivationStartDate: [],
    allowedActivationEndDate: [],
    isGroupSharingOffer: [],
    isMnpOffer: [],
    autoRenewal: [],
    transferAllowed: [],
    infoSharingAllowed: [],
    infoSharingOptions: [],
    offerPeriod: [],
    offerPeriodTerm: [],
    paymentType: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]]
  });

  constructor(
    protected offerSpecificationService: OfferSpecificationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ offerSpecification }) => {
      if (!offerSpecification.id) {
        const today = moment().startOf('day');
        offerSpecification.startDate = today;
        offerSpecification.endDate = today;
        offerSpecification.allowedActivationStartDate = today;
        offerSpecification.allowedActivationEndDate = today;
        offerSpecification.createdDate = today;
        offerSpecification.lastUpdatedDate = today;
      }

      this.updateForm(offerSpecification);
    });
  }

  updateForm(offerSpecification: IOfferSpecification): void {
    this.editForm.patchValue({
      id: offerSpecification.id,
      offerSpecId: offerSpecification.offerSpecId,
      offerId: offerSpecification.offerId,
      startDate: offerSpecification.startDate ? offerSpecification.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: offerSpecification.endDate ? offerSpecification.endDate.format(DATE_TIME_FORMAT) : null,
      limitedActivationPeriod: offerSpecification.limitedActivationPeriod,
      allowedActivationStartDate: offerSpecification.allowedActivationStartDate
        ? offerSpecification.allowedActivationStartDate.format(DATE_TIME_FORMAT)
        : null,
      allowedActivationEndDate: offerSpecification.allowedActivationEndDate
        ? offerSpecification.allowedActivationEndDate.format(DATE_TIME_FORMAT)
        : null,
      isGroupSharingOffer: offerSpecification.isGroupSharingOffer,
      isMnpOffer: offerSpecification.isMnpOffer,
      autoRenewal: offerSpecification.autoRenewal,
      transferAllowed: offerSpecification.transferAllowed,
      infoSharingAllowed: offerSpecification.infoSharingAllowed,
      infoSharingOptions: offerSpecification.infoSharingOptions,
      offerPeriod: offerSpecification.offerPeriod,
      offerPeriodTerm: offerSpecification.offerPeriodTerm,
      paymentType: offerSpecification.paymentType,
      createdDate: offerSpecification.createdDate ? offerSpecification.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: offerSpecification.createdBy,
      lastUpdatedDate: offerSpecification.lastUpdatedDate ? offerSpecification.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: offerSpecification.lastUpdatedBy,
      tenantId: offerSpecification.tenantId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const offerSpecification = this.createFromForm();
    if (offerSpecification.id !== undefined) {
      this.subscribeToSaveResponse(this.offerSpecificationService.update(offerSpecification));
    } else {
      this.subscribeToSaveResponse(this.offerSpecificationService.create(offerSpecification));
    }
  }

  private createFromForm(): IOfferSpecification {
    return {
      ...new OfferSpecification(),
      id: this.editForm.get(['id'])!.value,
      offerSpecId: this.editForm.get(['offerSpecId'])!.value,
      offerId: this.editForm.get(['offerId'])!.value,
      startDate: this.editForm.get(['startDate'])!.value ? moment(this.editForm.get(['startDate'])!.value, DATE_TIME_FORMAT) : undefined,
      endDate: this.editForm.get(['endDate'])!.value ? moment(this.editForm.get(['endDate'])!.value, DATE_TIME_FORMAT) : undefined,
      limitedActivationPeriod: this.editForm.get(['limitedActivationPeriod'])!.value,
      allowedActivationStartDate: this.editForm.get(['allowedActivationStartDate'])!.value
        ? moment(this.editForm.get(['allowedActivationStartDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      allowedActivationEndDate: this.editForm.get(['allowedActivationEndDate'])!.value
        ? moment(this.editForm.get(['allowedActivationEndDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      isGroupSharingOffer: this.editForm.get(['isGroupSharingOffer'])!.value,
      isMnpOffer: this.editForm.get(['isMnpOffer'])!.value,
      autoRenewal: this.editForm.get(['autoRenewal'])!.value,
      transferAllowed: this.editForm.get(['transferAllowed'])!.value,
      infoSharingAllowed: this.editForm.get(['infoSharingAllowed'])!.value,
      infoSharingOptions: this.editForm.get(['infoSharingOptions'])!.value,
      offerPeriod: this.editForm.get(['offerPeriod'])!.value,
      offerPeriodTerm: this.editForm.get(['offerPeriodTerm'])!.value,
      paymentType: this.editForm.get(['paymentType'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      lastUpdatedDate: this.editForm.get(['lastUpdatedDate'])!.value
        ? moment(this.editForm.get(['lastUpdatedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastUpdatedBy: this.editForm.get(['lastUpdatedBy'])!.value,
      tenantId: this.editForm.get(['tenantId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOfferSpecification>>): void {
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
}
