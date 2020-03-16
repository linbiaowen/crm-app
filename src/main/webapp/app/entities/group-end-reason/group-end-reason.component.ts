import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IGroupEndReason } from 'app/shared/model/group-end-reason.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { GroupEndReasonService } from './group-end-reason.service';
import { GroupEndReasonDeleteDialogComponent } from './group-end-reason-delete-dialog.component';

@Component({
  selector: 'jhi-group-end-reason',
  templateUrl: './group-end-reason.component.html'
})
export class GroupEndReasonComponent implements OnInit, OnDestroy {
  groupEndReasons?: IGroupEndReason[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected groupEndReasonService: GroupEndReasonService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.groupEndReasonService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IGroupEndReason[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInGroupEndReasons();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IGroupEndReason): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInGroupEndReasons(): void {
    this.eventSubscriber = this.eventManager.subscribe('groupEndReasonListModification', () => this.loadPage());
  }

  delete(groupEndReason: IGroupEndReason): void {
    const modalRef = this.modalService.open(GroupEndReasonDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.groupEndReason = groupEndReason;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IGroupEndReason[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/group-end-reason'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.groupEndReasons = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
