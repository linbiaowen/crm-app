import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOrderProcessConfig } from 'app/shared/model/order-process-config.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { OrderProcessConfigService } from './order-process-config.service';
import { OrderProcessConfigDeleteDialogComponent } from './order-process-config-delete-dialog.component';

@Component({
  selector: 'jhi-order-process-config',
  templateUrl: './order-process-config.component.html'
})
export class OrderProcessConfigComponent implements OnInit, OnDestroy {
  orderProcessConfigs?: IOrderProcessConfig[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected orderProcessConfigService: OrderProcessConfigService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.orderProcessConfigService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IOrderProcessConfig[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInOrderProcessConfigs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOrderProcessConfig): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInOrderProcessConfigs(): void {
    this.eventSubscriber = this.eventManager.subscribe('orderProcessConfigListModification', () => this.loadPage());
  }

  delete(orderProcessConfig: IOrderProcessConfig): void {
    const modalRef = this.modalService.open(OrderProcessConfigDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.orderProcessConfig = orderProcessConfig;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IOrderProcessConfig[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/order-process-config'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.orderProcessConfigs = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
