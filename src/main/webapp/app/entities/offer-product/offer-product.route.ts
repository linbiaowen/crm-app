import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOfferProduct, OfferProduct } from 'app/shared/model/offer-product.model';
import { OfferProductService } from './offer-product.service';
import { OfferProductComponent } from './offer-product.component';
import { OfferProductDetailComponent } from './offer-product-detail.component';
import { OfferProductUpdateComponent } from './offer-product-update.component';

@Injectable({ providedIn: 'root' })
export class OfferProductResolve implements Resolve<IOfferProduct> {
  constructor(private service: OfferProductService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOfferProduct> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((offerProduct: HttpResponse<OfferProduct>) => {
          if (offerProduct.body) {
            return of(offerProduct.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OfferProduct());
  }
}

export const offerProductRoute: Routes = [
  {
    path: '',
    component: OfferProductComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.offerProduct.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OfferProductDetailComponent,
    resolve: {
      offerProduct: OfferProductResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.offerProduct.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OfferProductUpdateComponent,
    resolve: {
      offerProduct: OfferProductResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.offerProduct.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OfferProductUpdateComponent,
    resolve: {
      offerProduct: OfferProductResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.offerProduct.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
