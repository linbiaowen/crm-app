import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOfferPromotion } from 'app/shared/model/offer-promotion.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { OfferPromotionService } from './offer-promotion.service';
import { OfferPromotionDeleteDialogComponent } from './offer-promotion-delete-dialog.component';

@Component({
  selector: 'jhi-offer-promotion',
  templateUrl: './offer-promotion.component.html'
})
export class OfferPromotionComponent implements OnInit, OnDestroy {
  offerPromotions?: IOfferPromotion[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected offerPromotionService: OfferPromotionService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.offerPromotionService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IOfferPromotion[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInOfferPromotions();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOfferPromotion): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInOfferPromotions(): void {
    this.eventSubscriber = this.eventManager.subscribe('offerPromotionListModification', () => this.loadPage());
  }

  delete(offerPromotion: IOfferPromotion): void {
    const modalRef = this.modalService.open(OfferPromotionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.offerPromotion = offerPromotion;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IOfferPromotion[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/offer-promotion'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.offerPromotions = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
