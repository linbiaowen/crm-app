import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICfsServiceSpec } from 'app/shared/model/cfs-service-spec.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { CfsServiceSpecService } from './cfs-service-spec.service';
import { CfsServiceSpecDeleteDialogComponent } from './cfs-service-spec-delete-dialog.component';

@Component({
  selector: 'jhi-cfs-service-spec',
  templateUrl: './cfs-service-spec.component.html'
})
export class CfsServiceSpecComponent implements OnInit, OnDestroy {
  cfsServiceSpecs?: ICfsServiceSpec[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected cfsServiceSpecService: CfsServiceSpecService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.cfsServiceSpecService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<ICfsServiceSpec[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInCfsServiceSpecs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICfsServiceSpec): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCfsServiceSpecs(): void {
    this.eventSubscriber = this.eventManager.subscribe('cfsServiceSpecListModification', () => this.loadPage());
  }

  delete(cfsServiceSpec: ICfsServiceSpec): void {
    const modalRef = this.modalService.open(CfsServiceSpecDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.cfsServiceSpec = cfsServiceSpec;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: ICfsServiceSpec[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/cfs-service-spec'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.cfsServiceSpecs = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
