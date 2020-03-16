import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOrderProcessStatus } from 'app/shared/model/order-process-status.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { OrderProcessStatusService } from './order-process-status.service';
import { OrderProcessStatusDeleteDialogComponent } from './order-process-status-delete-dialog.component';

@Component({
  selector: 'jhi-order-process-status',
  templateUrl: './order-process-status.component.html'
})
export class OrderProcessStatusComponent implements OnInit, OnDestroy {
  orderProcessStatuses?: IOrderProcessStatus[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected orderProcessStatusService: OrderProcessStatusService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.orderProcessStatusService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IOrderProcessStatus[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInOrderProcessStatuses();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOrderProcessStatus): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInOrderProcessStatuses(): void {
    this.eventSubscriber = this.eventManager.subscribe('orderProcessStatusListModification', () => this.loadPage());
  }

  delete(orderProcessStatus: IOrderProcessStatus): void {
    const modalRef = this.modalService.open(OrderProcessStatusDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.orderProcessStatus = orderProcessStatus;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IOrderProcessStatus[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/order-process-status'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.orderProcessStatuses = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
