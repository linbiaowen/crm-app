import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOfferDiscount, OfferDiscount } from 'app/shared/model/offer-discount.model';
import { OfferDiscountService } from './offer-discount.service';
import { OfferDiscountComponent } from './offer-discount.component';
import { OfferDiscountDetailComponent } from './offer-discount-detail.component';
import { OfferDiscountUpdateComponent } from './offer-discount-update.component';

@Injectable({ providedIn: 'root' })
export class OfferDiscountResolve implements Resolve<IOfferDiscount> {
  constructor(private service: OfferDiscountService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOfferDiscount> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((offerDiscount: HttpResponse<OfferDiscount>) => {
          if (offerDiscount.body) {
            return of(offerDiscount.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OfferDiscount());
  }
}

export const offerDiscountRoute: Routes = [
  {
    path: '',
    component: OfferDiscountComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.offerDiscount.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OfferDiscountDetailComponent,
    resolve: {
      offerDiscount: OfferDiscountResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.offerDiscount.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OfferDiscountUpdateComponent,
    resolve: {
      offerDiscount: OfferDiscountResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.offerDiscount.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OfferDiscountUpdateComponent,
    resolve: {
      offerDiscount: OfferDiscountResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.offerDiscount.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
