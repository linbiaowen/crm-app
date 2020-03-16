import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProductData, ProductData } from 'app/shared/model/product-data.model';
import { ProductDataService } from './product-data.service';
import { ProductDataComponent } from './product-data.component';
import { ProductDataDetailComponent } from './product-data-detail.component';
import { ProductDataUpdateComponent } from './product-data-update.component';

@Injectable({ providedIn: 'root' })
export class ProductDataResolve implements Resolve<IProductData> {
  constructor(private service: ProductDataService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProductData> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((productData: HttpResponse<ProductData>) => {
          if (productData.body) {
            return of(productData.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProductData());
  }
}

export const productDataRoute: Routes = [
  {
    path: '',
    component: ProductDataComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.productData.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProductDataDetailComponent,
    resolve: {
      productData: ProductDataResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.productData.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProductDataUpdateComponent,
    resolve: {
      productData: ProductDataResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.productData.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProductDataUpdateComponent,
    resolve: {
      productData: ProductDataResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.productData.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
