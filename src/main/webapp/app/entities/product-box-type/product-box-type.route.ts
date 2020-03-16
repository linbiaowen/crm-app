import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProductBoxType, ProductBoxType } from 'app/shared/model/product-box-type.model';
import { ProductBoxTypeService } from './product-box-type.service';
import { ProductBoxTypeComponent } from './product-box-type.component';
import { ProductBoxTypeDetailComponent } from './product-box-type-detail.component';
import { ProductBoxTypeUpdateComponent } from './product-box-type-update.component';

@Injectable({ providedIn: 'root' })
export class ProductBoxTypeResolve implements Resolve<IProductBoxType> {
  constructor(private service: ProductBoxTypeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProductBoxType> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((productBoxType: HttpResponse<ProductBoxType>) => {
          if (productBoxType.body) {
            return of(productBoxType.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProductBoxType());
  }
}

export const productBoxTypeRoute: Routes = [
  {
    path: '',
    component: ProductBoxTypeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.productBoxType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProductBoxTypeDetailComponent,
    resolve: {
      productBoxType: ProductBoxTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.productBoxType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProductBoxTypeUpdateComponent,
    resolve: {
      productBoxType: ProductBoxTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.productBoxType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProductBoxTypeUpdateComponent,
    resolve: {
      productBoxType: ProductBoxTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.productBoxType.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
