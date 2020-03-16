import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICfsServices } from 'app/shared/model/cfs-services.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { CfsServicesService } from './cfs-services.service';
import { CfsServicesDeleteDialogComponent } from './cfs-services-delete-dialog.component';

@Component({
  selector: 'jhi-cfs-services',
  templateUrl: './cfs-services.component.html'
})
export class CfsServicesComponent implements OnInit, OnDestroy {
  cfsServices?: ICfsServices[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected cfsServicesService: CfsServicesService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.cfsServicesService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<ICfsServices[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInCfsServices();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICfsServices): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCfsServices(): void {
    this.eventSubscriber = this.eventManager.subscribe('cfsServicesListModification', () => this.loadPage());
  }

  delete(cfsServices: ICfsServices): void {
    const modalRef = this.modalService.open(CfsServicesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.cfsServices = cfsServices;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: ICfsServices[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/cfs-services'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.cfsServices = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
