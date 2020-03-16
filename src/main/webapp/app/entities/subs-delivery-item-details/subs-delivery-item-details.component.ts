import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISubsDeliveryItemDetails } from 'app/shared/model/subs-delivery-item-details.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { SubsDeliveryItemDetailsService } from './subs-delivery-item-details.service';
import { SubsDeliveryItemDetailsDeleteDialogComponent } from './subs-delivery-item-details-delete-dialog.component';

@Component({
  selector: 'jhi-subs-delivery-item-details',
  templateUrl: './subs-delivery-item-details.component.html'
})
export class SubsDeliveryItemDetailsComponent implements OnInit, OnDestroy {
  subsDeliveryItemDetails?: ISubsDeliveryItemDetails[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected subsDeliveryItemDetailsService: SubsDeliveryItemDetailsService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.subsDeliveryItemDetailsService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<ISubsDeliveryItemDetails[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInSubsDeliveryItemDetails();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISubsDeliveryItemDetails): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSubsDeliveryItemDetails(): void {
    this.eventSubscriber = this.eventManager.subscribe('subsDeliveryItemDetailsListModification', () => this.loadPage());
  }

  delete(subsDeliveryItemDetails: ISubsDeliveryItemDetails): void {
    const modalRef = this.modalService.open(SubsDeliveryItemDetailsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.subsDeliveryItemDetails = subsDeliveryItemDetails;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: ISubsDeliveryItemDetails[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/subs-delivery-item-details'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.subsDeliveryItemDetails = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
