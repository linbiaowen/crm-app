import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOfferSpecification } from 'app/shared/model/offer-specification.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { OfferSpecificationService } from './offer-specification.service';
import { OfferSpecificationDeleteDialogComponent } from './offer-specification-delete-dialog.component';

@Component({
  selector: 'jhi-offer-specification',
  templateUrl: './offer-specification.component.html'
})
export class OfferSpecificationComponent implements OnInit, OnDestroy {
  offerSpecifications?: IOfferSpecification[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected offerSpecificationService: OfferSpecificationService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.offerSpecificationService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IOfferSpecification[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInOfferSpecifications();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOfferSpecification): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInOfferSpecifications(): void {
    this.eventSubscriber = this.eventManager.subscribe('offerSpecificationListModification', () => this.loadPage());
  }

  delete(offerSpecification: IOfferSpecification): void {
    const modalRef = this.modalService.open(OfferSpecificationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.offerSpecification = offerSpecification;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IOfferSpecification[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/offer-specification'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.offerSpecifications = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
