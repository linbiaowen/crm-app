import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISubsItemDelivery } from 'app/shared/model/subs-item-delivery.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { SubsItemDeliveryService } from './subs-item-delivery.service';
import { SubsItemDeliveryDeleteDialogComponent } from './subs-item-delivery-delete-dialog.component';

@Component({
  selector: 'jhi-subs-item-delivery',
  templateUrl: './subs-item-delivery.component.html'
})
export class SubsItemDeliveryComponent implements OnInit, OnDestroy {
  subsItemDeliveries?: ISubsItemDelivery[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected subsItemDeliveryService: SubsItemDeliveryService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.subsItemDeliveryService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<ISubsItemDelivery[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInSubsItemDeliveries();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISubsItemDelivery): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSubsItemDeliveries(): void {
    this.eventSubscriber = this.eventManager.subscribe('subsItemDeliveryListModification', () => this.loadPage());
  }

  delete(subsItemDelivery: ISubsItemDelivery): void {
    const modalRef = this.modalService.open(SubsItemDeliveryDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.subsItemDelivery = subsItemDelivery;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: ISubsItemDelivery[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/subs-item-delivery'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.subsItemDeliveries = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
