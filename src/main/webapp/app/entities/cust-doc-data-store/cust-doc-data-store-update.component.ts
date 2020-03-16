import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { ICustDocDataStore, CustDocDataStore } from 'app/shared/model/cust-doc-data-store.model';
import { CustDocDataStoreService } from './cust-doc-data-store.service';
import { AlertError } from 'app/shared/alert/alert-error.model';

@Component({
  selector: 'jhi-cust-doc-data-store-update',
  templateUrl: './cust-doc-data-store-update.component.html'
})
export class CustDocDataStoreUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    docDataStoreId: [null, [Validators.required]],
    documentData: [],
    documentDataContentType: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected custDocDataStoreService: CustDocDataStoreService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ custDocDataStore }) => {
      if (!custDocDataStore.id) {
        const today = moment().startOf('day');
        custDocDataStore.createdDate = today;
        custDocDataStore.lastUpdatedDate = today;
      }

      this.updateForm(custDocDataStore);
    });
  }

  updateForm(custDocDataStore: ICustDocDataStore): void {
    this.editForm.patchValue({
      id: custDocDataStore.id,
      docDataStoreId: custDocDataStore.docDataStoreId,
      documentData: custDocDataStore.documentData,
      documentDataContentType: custDocDataStore.documentDataContentType,
      createdDate: custDocDataStore.createdDate ? custDocDataStore.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: custDocDataStore.createdBy,
      lastUpdatedDate: custDocDataStore.lastUpdatedDate ? custDocDataStore.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: custDocDataStore.lastUpdatedBy,
      tenantId: custDocDataStore.tenantId
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('crmwebApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const custDocDataStore = this.createFromForm();
    if (custDocDataStore.id !== undefined) {
      this.subscribeToSaveResponse(this.custDocDataStoreService.update(custDocDataStore));
    } else {
      this.subscribeToSaveResponse(this.custDocDataStoreService.create(custDocDataStore));
    }
  }

  private createFromForm(): ICustDocDataStore {
    return {
      ...new CustDocDataStore(),
      id: this.editForm.get(['id'])!.value,
      docDataStoreId: this.editForm.get(['docDataStoreId'])!.value,
      documentDataContentType: this.editForm.get(['documentDataContentType'])!.value,
      documentData: this.editForm.get(['documentData'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustDocDataStore>>): void {
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
