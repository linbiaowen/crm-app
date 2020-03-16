import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ICustDocDataStore } from 'app/shared/model/cust-doc-data-store.model';

@Component({
  selector: 'jhi-cust-doc-data-store-detail',
  templateUrl: './cust-doc-data-store-detail.component.html'
})
export class CustDocDataStoreDetailComponent implements OnInit {
  custDocDataStore: ICustDocDataStore | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ custDocDataStore }) => (this.custDocDataStore = custDocDataStore));
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }
}
