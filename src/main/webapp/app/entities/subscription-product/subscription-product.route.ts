import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISubscriptionProduct, SubscriptionProduct } from 'app/shared/model/subscription-product.model';
import { SubscriptionProductService } from './subscription-product.service';
import { SubscriptionProductComponent } from './subscription-product.component';
import { SubscriptionProductDetailComponent } from './subscription-product-detail.component';
import { SubscriptionProductUpdateComponent } from './subscription-product-update.component';

@Injectable({ providedIn: 'root' })
export class SubscriptionProductResolve implements Resolve<ISubscriptionProduct> {
  constructor(private service: SubscriptionProductService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISubscriptionProduct> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((subscriptionProduct: HttpResponse<SubscriptionProduct>) => {
          if (subscriptionProduct.body) {
            return of(subscriptionProduct.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SubscriptionProduct());
  }
}

export const subscriptionProductRoute: Routes = [
  {
    path: '',
    component: SubscriptionProductComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.subscriptionProduct.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SubscriptionProductDetailComponent,
    resolve: {
      subscriptionProduct: SubscriptionProductResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.subscriptionProduct.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SubscriptionProductUpdateComponent,
    resolve: {
      subscriptionProduct: SubscriptionProductResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.subscriptionProduct.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SubscriptionProductUpdateComponent,
    resolve: {
      subscriptionProduct: SubscriptionProductResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.subscriptionProduct.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
