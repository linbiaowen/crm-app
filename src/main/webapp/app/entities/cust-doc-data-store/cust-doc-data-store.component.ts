import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICustDocDataStore } from 'app/shared/model/cust-doc-data-store.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { CustDocDataStoreService } from './cust-doc-data-store.service';
import { CustDocDataStoreDeleteDialogComponent } from './cust-doc-data-store-delete-dialog.component';

@Component({
  selector: 'jhi-cust-doc-data-store',
  templateUrl: './cust-doc-data-store.component.html'
})
export class CustDocDataStoreComponent implements OnInit, OnDestroy {
  custDocDataStores?: ICustDocDataStore[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected custDocDataStoreService: CustDocDataStoreService,
    protected activatedRoute: ActivatedRoute,
    protected dataUtils: JhiDataUtils,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.custDocDataStoreService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<ICustDocDataStore[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInCustDocDataStores();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICustDocDataStore): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    return this.dataUtils.openFile(contentType, base64String);
  }

  registerChangeInCustDocDataStores(): void {
    this.eventSubscriber = this.eventManager.subscribe('custDocDataStoreListModification', () => this.loadPage());
  }

  delete(custDocDataStore: ICustDocDataStore): void {
    const modalRef = this.modalService.open(CustDocDataStoreDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.custDocDataStore = custDocDataStore;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: ICustDocDataStore[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/cust-doc-data-store'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.custDocDataStores = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
