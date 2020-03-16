import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOfferProduct } from 'app/shared/model/offer-product.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { OfferProductService } from './offer-product.service';
import { OfferProductDeleteDialogComponent } from './offer-product-delete-dialog.component';

@Component({
  selector: 'jhi-offer-product',
  templateUrl: './offer-product.component.html'
})
export class OfferProductComponent implements OnInit, OnDestroy {
  offerProducts?: IOfferProduct[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected offerProductService: OfferProductService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.offerProductService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IOfferProduct[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInOfferProducts();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOfferProduct): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInOfferProducts(): void {
    this.eventSubscriber = this.eventManager.subscribe('offerProductListModification', () => this.loadPage());
  }

  delete(offerProduct: IOfferProduct): void {
    const modalRef = this.modalService.open(OfferProductDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.offerProduct = offerProduct;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IOfferProduct[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/offer-product'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.offerProducts = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
