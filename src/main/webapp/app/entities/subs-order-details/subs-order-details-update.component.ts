import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISubsOrderDetails, SubsOrderDetails } from 'app/shared/model/subs-order-details.model';
import { SubsOrderDetailsService } from './subs-order-details.service';
import { ICustSubscription } from 'app/shared/model/cust-subscription.model';
import { CustSubscriptionService } from 'app/entities/cust-subscription/cust-subscription.service';
import { IOrderMaster } from 'app/shared/model/order-master.model';
import { OrderMasterService } from 'app/entities/order-master/order-master.service';

type SelectableEntity = ICustSubscription | IOrderMaster;

@Component({
  selector: 'jhi-subs-order-details-update',
  templateUrl: './subs-order-details-update.component.html'
})
export class SubsOrderDetailsUpdateComponent implements OnInit {
  isSaving = false;
  custsubscriptions: ICustSubscription[] = [];
  ordermasters: IOrderMaster[] = [];

  editForm = this.fb.group({
    id: [],
    subsOrderSeqId: [null, [Validators.required]],
    subscriptionId: [null, [Validators.required]],
    startDate: [],
    endDate: [],
    orderId: [null, [Validators.required]],
    ssaNbr: [],
    primaryMsisdn: [],
    iccid: [],
    imsi: [],
    simVerified: [],
    simVerifiedDate: [],
    billingAcctId: [],
    billCycleId: [],
    mnpRequestedDate: [],
    mnpTicket: [],
    mnpPortInSession: [],
    mnpOriginalId: [],
    mnpCustName: [],
    mnpIdNbr: [],
    mnpIdType: [],
    hthkMsisdn: [],
    lang: [null, [Validators.required]],
    offerId: [],
    offerName: [],
    matrixxCatalogId: [],
    matrixxResourceId: [],
    matrixxObjectId: [],
    salesChannel: [],
    advancePaymentMonths: [],
    offerPrice: [],
    networkType: [],
    servicetype: [],
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
    custSubscription: [],
    orderMaster: []
  });

  constructor(
    protected subsOrderDetailsService: SubsOrderDetailsService,
    protected custSubscriptionService: CustSubscriptionService,
    protected orderMasterService: OrderMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ subsOrderDetails }) => {
      if (!subsOrderDetails.id) {
        const today = moment().startOf('day');
        subsOrderDetails.startDate = today;
        subsOrderDetails.endDate = today;
        subsOrderDetails.simVerifiedDate = today;
        subsOrderDetails.mnpRequestedDate = today;
        subsOrderDetails.createdDate = today;
        subsOrderDetails.lastUpdatedDate = today;
      }

      this.updateForm(subsOrderDetails);

      this.custSubscriptionService.query().subscribe((res: HttpResponse<ICustSubscription[]>) => (this.custsubscriptions = res.body || []));

      this.orderMasterService.query().subscribe((res: HttpResponse<IOrderMaster[]>) => (this.ordermasters = res.body || []));
    });
  }

  updateForm(subsOrderDetails: ISubsOrderDetails): void {
    this.editForm.patchValue({
      id: subsOrderDetails.id,
      subsOrderSeqId: subsOrderDetails.subsOrderSeqId,
      subscriptionId: subsOrderDetails.subscriptionId,
      startDate: subsOrderDetails.startDate ? subsOrderDetails.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: subsOrderDetails.endDate ? subsOrderDetails.endDate.format(DATE_TIME_FORMAT) : null,
      orderId: subsOrderDetails.orderId,
      ssaNbr: subsOrderDetails.ssaNbr,
      primaryMsisdn: subsOrderDetails.primaryMsisdn,
      iccid: subsOrderDetails.iccid,
      imsi: subsOrderDetails.imsi,
      simVerified: subsOrderDetails.simVerified,
      simVerifiedDate: subsOrderDetails.simVerifiedDate ? subsOrderDetails.simVerifiedDate.format(DATE_TIME_FORMAT) : null,
      billingAcctId: subsOrderDetails.billingAcctId,
      billCycleId: subsOrderDetails.billCycleId,
      mnpRequestedDate: subsOrderDetails.mnpRequestedDate ? subsOrderDetails.mnpRequestedDate.format(DATE_TIME_FORMAT) : null,
      mnpTicket: subsOrderDetails.mnpTicket,
      mnpPortInSession: subsOrderDetails.mnpPortInSession,
      mnpOriginalId: subsOrderDetails.mnpOriginalId,
      mnpCustName: subsOrderDetails.mnpCustName,
      mnpIdNbr: subsOrderDetails.mnpIdNbr,
      mnpIdType: subsOrderDetails.mnpIdType,
      hthkMsisdn: subsOrderDetails.hthkMsisdn,
      lang: subsOrderDetails.lang,
      offerId: subsOrderDetails.offerId,
      offerName: subsOrderDetails.offerName,
      matrixxCatalogId: subsOrderDetails.matrixxCatalogId,
      matrixxResourceId: subsOrderDetails.matrixxResourceId,
      matrixxObjectId: subsOrderDetails.matrixxObjectId,
      salesChannel: subsOrderDetails.salesChannel,
      advancePaymentMonths: subsOrderDetails.advancePaymentMonths,
      offerPrice: subsOrderDetails.offerPrice,
      networkType: subsOrderDetails.networkType,
      servicetype: subsOrderDetails.servicetype,
      offerPlanCode: subsOrderDetails.offerPlanCode,
      serviceInPerson: subsOrderDetails.serviceInPerson,
      fcmToken: subsOrderDetails.fcmToken,
      remarks: subsOrderDetails.remarks,
      cdVersion: subsOrderDetails.cdVersion,
      createdDate: subsOrderDetails.createdDate ? subsOrderDetails.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: subsOrderDetails.createdBy,
      lastUpdatedDate: subsOrderDetails.lastUpdatedDate ? subsOrderDetails.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: subsOrderDetails.lastUpdatedBy,
      tenantId: subsOrderDetails.tenantId,
      custSubscription: subsOrderDetails.custSubscription,
      orderMaster: subsOrderDetails.orderMaster
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const subsOrderDetails = this.createFromForm();
    if (subsOrderDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.subsOrderDetailsService.update(subsOrderDetails));
    } else {
      this.subscribeToSaveResponse(this.subsOrderDetailsService.create(subsOrderDetails));
    }
  }

  private createFromForm(): ISubsOrderDetails {
    return {
      ...new SubsOrderDetails(),
      id: this.editForm.get(['id'])!.value,
      subsOrderSeqId: this.editForm.get(['subsOrderSeqId'])!.value,
      subscriptionId: this.editForm.get(['subscriptionId'])!.value,
      startDate: this.editForm.get(['startDate'])!.value ? moment(this.editForm.get(['startDate'])!.value, DATE_TIME_FORMAT) : undefined,
      endDate: this.editForm.get(['endDate'])!.value ? moment(this.editForm.get(['endDate'])!.value, DATE_TIME_FORMAT) : undefined,
      orderId: this.editForm.get(['orderId'])!.value,
      ssaNbr: this.editForm.get(['ssaNbr'])!.value,
      primaryMsisdn: this.editForm.get(['primaryMsisdn'])!.value,
      iccid: this.editForm.get(['iccid'])!.value,
      imsi: this.editForm.get(['imsi'])!.value,
      simVerified: this.editForm.get(['simVerified'])!.value,
      simVerifiedDate: this.editForm.get(['simVerifiedDate'])!.value
        ? moment(this.editForm.get(['simVerifiedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      billingAcctId: this.editForm.get(['billingAcctId'])!.value,
      billCycleId: this.editForm.get(['billCycleId'])!.value,
      mnpRequestedDate: this.editForm.get(['mnpRequestedDate'])!.value
        ? moment(this.editForm.get(['mnpRequestedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      mnpTicket: this.editForm.get(['mnpTicket'])!.value,
      mnpPortInSession: this.editForm.get(['mnpPortInSession'])!.value,
      mnpOriginalId: this.editForm.get(['mnpOriginalId'])!.value,
      mnpCustName: this.editForm.get(['mnpCustName'])!.value,
      mnpIdNbr: this.editForm.get(['mnpIdNbr'])!.value,
      mnpIdType: this.editForm.get(['mnpIdType'])!.value,
      hthkMsisdn: this.editForm.get(['hthkMsisdn'])!.value,
      lang: this.editForm.get(['lang'])!.value,
      offerId: this.editForm.get(['offerId'])!.value,
      offerName: this.editForm.get(['offerName'])!.value,
      matrixxCatalogId: this.editForm.get(['matrixxCatalogId'])!.value,
      matrixxResourceId: this.editForm.get(['matrixxResourceId'])!.value,
      matrixxObjectId: this.editForm.get(['matrixxObjectId'])!.value,
      salesChannel: this.editForm.get(['salesChannel'])!.value,
      advancePaymentMonths: this.editForm.get(['advancePaymentMonths'])!.value,
      offerPrice: this.editForm.get(['offerPrice'])!.value,
      networkType: this.editForm.get(['networkType'])!.value,
      servicetype: this.editForm.get(['servicetype'])!.value,
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
      custSubscription: this.editForm.get(['custSubscription'])!.value,
      orderMaster: this.editForm.get(['orderMaster'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISubsOrderDetails>>): void {
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
