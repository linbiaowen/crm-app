import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISubscriptionProduct } from 'app/shared/model/subscription-product.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { SubscriptionProductService } from './subscription-product.service';
import { SubscriptionProductDeleteDialogComponent } from './subscription-product-delete-dialog.component';

@Component({
  selector: 'jhi-subscription-product',
  templateUrl: './subscription-product.component.html'
})
export class SubscriptionProductComponent implements OnInit, OnDestroy {
  subscriptionProducts?: ISubscriptionProduct[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected subscriptionProductService: SubscriptionProductService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.subscriptionProductService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<ISubscriptionProduct[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInSubscriptionProducts();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISubscriptionProduct): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSubscriptionProducts(): void {
    this.eventSubscriber = this.eventManager.subscribe('subscriptionProductListModification', () => this.loadPage());
  }

  delete(subscriptionProduct: ISubscriptionProduct): void {
    const modalRef = this.modalService.open(SubscriptionProductDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.subscriptionProduct = subscriptionProduct;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: ISubscriptionProduct[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/subscription-product'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.subscriptionProducts = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
