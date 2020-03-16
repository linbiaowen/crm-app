import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISubsPurchaseControl } from 'app/shared/model/subs-purchase-control.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { SubsPurchaseControlService } from './subs-purchase-control.service';
import { SubsPurchaseControlDeleteDialogComponent } from './subs-purchase-control-delete-dialog.component';

@Component({
  selector: 'jhi-subs-purchase-control',
  templateUrl: './subs-purchase-control.component.html'
})
export class SubsPurchaseControlComponent implements OnInit, OnDestroy {
  subsPurchaseControls?: ISubsPurchaseControl[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected subsPurchaseControlService: SubsPurchaseControlService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.subsPurchaseControlService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<ISubsPurchaseControl[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInSubsPurchaseControls();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISubsPurchaseControl): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSubsPurchaseControls(): void {
    this.eventSubscriber = this.eventManager.subscribe('subsPurchaseControlListModification', () => this.loadPage());
  }

  delete(subsPurchaseControl: ISubsPurchaseControl): void {
    const modalRef = this.modalService.open(SubsPurchaseControlDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.subsPurchaseControl = subsPurchaseControl;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: ISubsPurchaseControl[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/subs-purchase-control'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.subsPurchaseControls = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
