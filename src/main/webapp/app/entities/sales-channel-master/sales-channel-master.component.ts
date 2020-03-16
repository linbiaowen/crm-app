import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISalesChannelMaster } from 'app/shared/model/sales-channel-master.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { SalesChannelMasterService } from './sales-channel-master.service';
import { SalesChannelMasterDeleteDialogComponent } from './sales-channel-master-delete-dialog.component';

@Component({
  selector: 'jhi-sales-channel-master',
  templateUrl: './sales-channel-master.component.html'
})
export class SalesChannelMasterComponent implements OnInit, OnDestroy {
  salesChannelMasters?: ISalesChannelMaster[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected salesChannelMasterService: SalesChannelMasterService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.salesChannelMasterService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<ISalesChannelMaster[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInSalesChannelMasters();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISalesChannelMaster): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSalesChannelMasters(): void {
    this.eventSubscriber = this.eventManager.subscribe('salesChannelMasterListModification', () => this.loadPage());
  }

  delete(salesChannelMaster: ISalesChannelMaster): void {
    const modalRef = this.modalService.open(SalesChannelMasterDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.salesChannelMaster = salesChannelMaster;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: ISalesChannelMaster[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/sales-channel-master'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.salesChannelMasters = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
