import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISubscriptionDetails, SubscriptionDetails } from 'app/shared/model/subscription-details.model';
import { SubscriptionDetailsService } from './subscription-details.service';
import { ICustSubscription } from 'app/shared/model/cust-subscription.model';
import { CustSubscriptionService } from 'app/entities/cust-subscription/cust-subscription.service';

@Component({
  selector: 'jhi-subscription-details-update',
  templateUrl: './subscription-details-update.component.html'
})
export class SubscriptionDetailsUpdateComponent implements OnInit {
  isSaving = false;
  custsubscriptions: ICustSubscription[] = [];

  editForm = this.fb.group({
    id: [],
    subsDetailId: [null, [Validators.required]],
    subscriptionId: [null, [Validators.required]],
    startDate: [],
    endDate: [],
    orderId: [],
    ssaNbr: [],
    primaryMsisdn: [],
    iccid: [],
    imsi: [],
    mnpRequestedDate: [],
    lang: [null, [Validators.required]],
    baseOfferId: [],
    baseOfferName: [],
    matrixxCatalogId: [],
    matrixxResourceId: [],
    matrixxObjectId: [],
    salesChannel: [],
    advancePaymentMonths: [],
    offerPrice: [],
    networkType: [],
    serviceType: [],
    offerPlanCode: [],
    serviceInPerson: [],
    fcmToken: [],
    remarks: [],
    cdVersion: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]],
    custSubscription: []
  });

  constructor(
    protected subscriptionDetailsService: SubscriptionDetailsService,
    protected custSubscriptionService: CustSubscriptionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ subscriptionDetails }) => {
      if (!subscriptionDetails.id) {
        const today = moment().startOf('day');
        subscriptionDetails.startDate = today;
        subscriptionDetails.endDate = today;
        subscriptionDetails.mnpRequestedDate = today;
        subscriptionDetails.createdDate = today;
        subscriptionDetails.lastUpdatedDate = today;
      }

      this.updateForm(subscriptionDetails);

      this.custSubscriptionService.query().subscribe((res: HttpResponse<ICustSubscription[]>) => (this.custsubscriptions = res.body || []));
    });
  }

  updateForm(subscriptionDetails: ISubscriptionDetails): void {
    this.editForm.patchValue({
      id: subscriptionDetails.id,
      subsDetailId: subscriptionDetails.subsDetailId,
      subscriptionId: subscriptionDetails.subscriptionId,
      startDate: subscriptionDetails.startDate ? subscriptionDetails.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: subscriptionDetails.endDate ? subscriptionDetails.endDate.format(DATE_TIME_FORMAT) : null,
      orderId: subscriptionDetails.orderId,
      ssaNbr: subscriptionDetails.ssaNbr,
      primaryMsisdn: subscriptionDetails.primaryMsisdn,
      iccid: subscriptionDetails.iccid,
      imsi: subscriptionDetails.imsi,
      mnpRequestedDate: subscriptionDetails.mnpRequestedDate ? subscriptionDetails.mnpRequestedDate.format(DATE_TIME_FORMAT) : null,
      lang: subscriptionDetails.lang,
      baseOfferId: subscriptionDetails.baseOfferId,
      baseOfferName: subscriptionDetails.baseOfferName,
      matrixxCatalogId: subscriptionDetails.matrixxCatalogId,
      matrixxResourceId: subscriptionDetails.matrixxResourceId,
      matrixxObjectId: subscriptionDetails.matrixxObjectId,
      salesChannel: subscriptionDetails.salesChannel,
      advancePaymentMonths: subscriptionDetails.advancePaymentMonths,
      offerPrice: subscriptionDetails.offerPrice,
      networkType: subscriptionDetails.networkType,
      serviceType: subscriptionDetails.serviceType,
      offerPlanCode: subscriptionDetails.offerPlanCode,
      serviceInPerson: subscriptionDetails.serviceInPerson,
      fcmToken: subscriptionDetails.fcmToken,
      remarks: subscriptionDetails.remarks,
      cdVersion: subscriptionDetails.cdVersion,
      createdDate: subscriptionDetails.createdDate ? subscriptionDetails.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: subscriptionDetails.createdBy,
      lastUpdatedDate: subscriptionDetails.lastUpdatedDate ? subscriptionDetails.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: subscriptionDetails.lastUpdatedBy,
      tenantId: subscriptionDetails.tenantId,
      custSubscription: subscriptionDetails.custSubscription
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const subscriptionDetails = this.createFromForm();
    if (subscriptionDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.subscriptionDetailsService.update(subscriptionDetails));
    } else {
      this.subscribeToSaveResponse(this.subscriptionDetailsService.create(subscriptionDetails));
    }
  }

  private createFromForm(): ISubscriptionDetails {
    return {
      ...new SubscriptionDetails(),
      id: this.editForm.get(['id'])!.value,
      subsDetailId: this.editForm.get(['subsDetailId'])!.value,
      subscriptionId: this.editForm.get(['subscriptionId'])!.value,
      startDate: this.editForm.get(['startDate'])!.value ? moment(this.editForm.get(['startDate'])!.value, DATE_TIME_FORMAT) : undefined,
      endDate: this.editForm.get(['endDate'])!.value ? moment(this.editForm.get(['endDate'])!.value, DATE_TIME_FORMAT) : undefined,
      orderId: this.editForm.get(['orderId'])!.value,
      ssaNbr: this.editForm.get(['ssaNbr'])!.value,
      primaryMsisdn: this.editForm.get(['primaryMsisdn'])!.value,
      iccid: this.editForm.get(['iccid'])!.value,
      imsi: this.editForm.get(['imsi'])!.value,
      mnpRequestedDate: this.editForm.get(['mnpRequestedDate'])!.value
        ? moment(this.editForm.get(['mnpRequestedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lang: this.editForm.get(['lang'])!.value,
      baseOfferId: this.editForm.get(['baseOfferId'])!.value,
      baseOfferName: this.editForm.get(['baseOfferName'])!.value,
      matrixxCatalogId: this.editForm.get(['matrixxCatalogId'])!.value,
      matrixxResourceId: this.editForm.get(['matrixxResourceId'])!.value,
      matrixxObjectId: this.editForm.get(['matrixxObjectId'])!.value,
      salesChannel: this.editForm.get(['salesChannel'])!.value,
      advancePaymentMonths: this.editForm.get(['advancePaymentMonths'])!.value,
      offerPrice: this.editForm.get(['offerPrice'])!.value,
      networkType: this.editForm.get(['networkType'])!.value,
      serviceType: this.editForm.get(['serviceType'])!.value,
      offerPlanCode: this.editForm.get(['offerPlanCode'])!.value,
      serviceInPerson: this.editForm.get(['serviceInPerson'])!.value,
      fcmToken: this.editForm.get(['fcmToken'])!.value,
      remarks: this.editForm.get(['remarks'])!.value,
      cdVersion: this.editForm.get(['cdVersion'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      lastUpdatedDate: this.editForm.get(['lastUpdatedDate'])!.value
        ? moment(this.editForm.get(['lastUpdatedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastUpdatedBy: this.editForm.get(['lastUpdatedBy'])!.value,
      tenantId: this.editForm.get(['tenantId'])!.value,
      custSubscription: this.editForm.get(['custSubscription'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISubscriptionDetails>>): void {
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

  trackById(index: number, item: ICustSubscription): any {
    return item.id;
  }
}
