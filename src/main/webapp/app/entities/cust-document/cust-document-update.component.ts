import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICustDocument, CustDocument } from 'app/shared/model/cust-document.model';
import { CustDocumentService } from './cust-document.service';
import { ICustDocDataStore } from 'app/shared/model/cust-doc-data-store.model';
import { CustDocDataStoreService } from 'app/entities/cust-doc-data-store/cust-doc-data-store.service';
import { ICustomer } from 'app/shared/model/customer.model';
import { CustomerService } from 'app/entities/customer/customer.service';
import { ICustSubscription } from 'app/shared/model/cust-subscription.model';
import { CustSubscriptionService } from 'app/entities/cust-subscription/cust-subscription.service';

type SelectableEntity = ICustDocDataStore | ICustomer | ICustSubscription;

@Component({
  selector: 'jhi-cust-document-update',
  templateUrl: './cust-document-update.component.html'
})
export class CustDocumentUpdateComponent implements OnInit {
  isSaving = false;
  docdatastores: ICustDocDataStore[] = [];
  customers: ICustomer[] = [];
  custsubscriptions: ICustSubscription[] = [];

  editForm = this.fb.group({
    id: [],
    custDocId: [null, [Validators.required]],
    custAcctId: [],
    subscriptionId: [],
    docType: [null, [Validators.required]],
    docIdNumber: [],
    docDataStoreId: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]],
    docDataStore: [],
    customer: [],
    custSubscription: []
  });

  constructor(
    protected custDocumentService: CustDocumentService,
    protected custDocDataStoreService: CustDocDataStoreService,
    protected customerService: CustomerService,
    protected custSubscriptionService: CustSubscriptionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ custDocument }) => {
      if (!custDocument.id) {
        const today = moment().startOf('day');
        custDocument.createdDate = today;
        custDocument.lastUpdatedDate = today;
      }

      this.updateForm(custDocument);

      this.custDocDataStoreService
        .query({ filter: 'custdocument-is-null' })
        .pipe(
          map((res: HttpResponse<ICustDocDataStore[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ICustDocDataStore[]) => {
          if (!custDocument.docDataStore || !custDocument.docDataStore.id) {
            this.docdatastores = resBody;
          } else {
            this.custDocDataStoreService
              .find(custDocument.docDataStore.id)
              .pipe(
                map((subRes: HttpResponse<ICustDocDataStore>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ICustDocDataStore[]) => (this.docdatastores = concatRes));
          }
        });

      this.customerService.query().subscribe((res: HttpResponse<ICustomer[]>) => (this.customers = res.body || []));

      this.custSubscriptionService.query().subscribe((res: HttpResponse<ICustSubscription[]>) => (this.custsubscriptions = res.body || []));
    });
  }

  updateForm(custDocument: ICustDocument): void {
    this.editForm.patchValue({
      id: custDocument.id,
      custDocId: custDocument.custDocId,
      custAcctId: custDocument.custAcctId,
      subscriptionId: custDocument.subscriptionId,
      docType: custDocument.docType,
      docIdNumber: custDocument.docIdNumber,
      docDataStoreId: custDocument.docDataStoreId,
      createdDate: custDocument.createdDate ? custDocument.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: custDocument.createdBy,
      lastUpdatedDate: custDocument.lastUpdatedDate ? custDocument.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: custDocument.lastUpdatedBy,
      tenantId: custDocument.tenantId,
      docDataStore: custDocument.docDataStore,
      customer: custDocument.customer,
      custSubscription: custDocument.custSubscription
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const custDocument = this.createFromForm();
    if (custDocument.id !== undefined) {
      this.subscribeToSaveResponse(this.custDocumentService.update(custDocument));
    } else {
      this.subscribeToSaveResponse(this.custDocumentService.create(custDocument));
    }
  }

  private createFromForm(): ICustDocument {
    return {
      ...new CustDocument(),
      id: this.editForm.get(['id'])!.value,
      custDocId: this.editForm.get(['custDocId'])!.value,
      custAcctId: this.editForm.get(['custAcctId'])!.value,
      subscriptionId: this.editForm.get(['subscriptionId'])!.value,
      docType: this.editForm.get(['docType'])!.value,
      docIdNumber: this.editForm.get(['docIdNumber'])!.value,
      docDataStoreId: this.editForm.get(['docDataStoreId'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      lastUpdatedDate: this.editForm.get(['lastUpdatedDate'])!.value
        ? moment(this.editForm.get(['lastUpdatedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastUpdatedBy: this.editForm.get(['lastUpdatedBy'])!.value,
      tenantId: this.editForm.get(['tenantId'])!.value,
      docDataStore: this.editForm.get(['docDataStore'])!.value,
      customer: this.editForm.get(['customer'])!.value,
      custSubscription: this.editForm.get(['custSubscription'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustDocument>>): void {
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
