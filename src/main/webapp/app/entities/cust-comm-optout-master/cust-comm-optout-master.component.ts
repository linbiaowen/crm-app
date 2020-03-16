import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICustCommOptoutMaster } from 'app/shared/model/cust-comm-optout-master.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { CustCommOptoutMasterService } from './cust-comm-optout-master.service';
import { CustCommOptoutMasterDeleteDialogComponent } from './cust-comm-optout-master-delete-dialog.component';

@Component({
  selector: 'jhi-cust-comm-optout-master',
  templateUrl: './cust-comm-optout-master.component.html'
})
export class CustCommOptoutMasterComponent implements OnInit, OnDestroy {
  custCommOptoutMasters?: ICustCommOptoutMaster[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected custCommOptoutMasterService: CustCommOptoutMasterService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.custCommOptoutMasterService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<ICustCommOptoutMaster[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInCustCommOptoutMasters();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICustCommOptoutMaster): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCustCommOptoutMasters(): void {
    this.eventSubscriber = this.eventManager.subscribe('custCommOptoutMasterListModification', () => this.loadPage());
  }

  delete(custCommOptoutMaster: ICustCommOptoutMaster): void {
    const modalRef = this.modalService.open(CustCommOptoutMasterDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.custCommOptoutMaster = custCommOptoutMaster;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: ICustCommOptoutMaster[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/cust-comm-optout-master'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.custCommOptoutMasters = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
