import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProductSms, ProductSms } from 'app/shared/model/product-sms.model';
import { ProductSmsService } from './product-sms.service';
import { ProductSmsComponent } from './product-sms.component';
import { ProductSmsDetailComponent } from './product-sms-detail.component';
import { ProductSmsUpdateComponent } from './product-sms-update.component';

@Injectable({ providedIn: 'root' })
export class ProductSmsResolve implements Resolve<IProductSms> {
  constructor(private service: ProductSmsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProductSms> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((productSms: HttpResponse<ProductSms>) => {
          if (productSms.body) {
            return of(productSms.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProductSms());
  }
}

export const productSmsRoute: Routes = [
  {
    path: '',
    component: ProductSmsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.productSms.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProductSmsDetailComponent,
    resolve: {
      productSms: ProductSmsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.productSms.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProductSmsUpdateComponent,
    resolve: {
      productSms: ProductSmsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.productSms.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProductSmsUpdateComponent,
    resolve: {
      productSms: ProductSmsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.productSms.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
