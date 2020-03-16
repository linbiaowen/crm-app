import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProductSimAttributes } from 'app/shared/model/product-sim-attributes.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ProductSimAttributesService } from './product-sim-attributes.service';
import { ProductSimAttributesDeleteDialogComponent } from './product-sim-attributes-delete-dialog.component';

@Component({
  selector: 'jhi-product-sim-attributes',
  templateUrl: './product-sim-attributes.component.html'
})
export class ProductSimAttributesComponent implements OnInit, OnDestroy {
  productSimAttributes?: IProductSimAttributes[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected productSimAttributesService: ProductSimAttributesService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.productSimAttributesService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IProductSimAttributes[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInProductSimAttributes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IProductSimAttributes): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProductSimAttributes(): void {
    this.eventSubscriber = this.eventManager.subscribe('productSimAttributesListModification', () => this.loadPage());
  }

  delete(productSimAttributes: IProductSimAttributes): void {
    const modalRef = this.modalService.open(ProductSimAttributesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.productSimAttributes = productSimAttributes;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IProductSimAttributes[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/product-sim-attributes'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.productSimAttributes = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
