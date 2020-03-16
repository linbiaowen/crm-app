import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOfferPromotion, OfferPromotion } from 'app/shared/model/offer-promotion.model';
import { OfferPromotionService } from './offer-promotion.service';
import { OfferPromotionComponent } from './offer-promotion.component';
import { OfferPromotionDetailComponent } from './offer-promotion-detail.component';
import { OfferPromotionUpdateComponent } from './offer-promotion-update.component';

@Injectable({ providedIn: 'root' })
export class OfferPromotionResolve implements Resolve<IOfferPromotion> {
  constructor(private service: OfferPromotionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOfferPromotion> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((offerPromotion: HttpResponse<OfferPromotion>) => {
          if (offerPromotion.body) {
            return of(offerPromotion.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OfferPromotion());
  }
}

export const offerPromotionRoute: Routes = [
  {
    path: '',
    component: OfferPromotionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.offerPromotion.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OfferPromotionDetailComponent,
    resolve: {
      offerPromotion: OfferPromotionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.offerPromotion.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OfferPromotionUpdateComponent,
    resolve: {
      offerPromotion: OfferPromotionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.offerPromotion.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OfferPromotionUpdateComponent,
    resolve: {
      offerPromotion: OfferPromotionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.offerPromotion.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
