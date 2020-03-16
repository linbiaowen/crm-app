import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISubscriptionProvision } from 'app/shared/model/subscription-provision.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { SubscriptionProvisionService } from './subscription-provision.service';
import { SubscriptionProvisionDeleteDialogComponent } from './subscription-provision-delete-dialog.component';

@Component({
  selector: 'jhi-subscription-provision',
  templateUrl: './subscription-provision.component.html'
})
export class SubscriptionProvisionComponent implements OnInit, OnDestroy {
  subscriptionProvisions?: ISubscriptionProvision[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected subscriptionProvisionService: SubscriptionProvisionService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.subscriptionProvisionService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<ISubscriptionProvision[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInSubscriptionProvisions();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISubscriptionProvision): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSubscriptionProvisions(): void {
    this.eventSubscriber = this.eventManager.subscribe('subscriptionProvisionListModification', () => this.loadPage());
  }

  delete(subscriptionProvision: ISubscriptionProvision): void {
    const modalRef = this.modalService.open(SubscriptionProvisionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.subscriptionProvision = subscriptionProvision;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: ISubscriptionProvision[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/subscription-provision'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.subscriptionProvisions = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
