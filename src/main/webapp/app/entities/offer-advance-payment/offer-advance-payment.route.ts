import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOfferAdvancePayment, OfferAdvancePayment } from 'app/shared/model/offer-advance-payment.model';
import { OfferAdvancePaymentService } from './offer-advance-payment.service';
import { OfferAdvancePaymentComponent } from './offer-advance-payment.component';
import { OfferAdvancePaymentDetailComponent } from './offer-advance-payment-detail.component';
import { OfferAdvancePaymentUpdateComponent } from './offer-advance-payment-update.component';

@Injectable({ providedIn: 'root' })
export class OfferAdvancePaymentResolve implements Resolve<IOfferAdvancePayment> {
  constructor(private service: OfferAdvancePaymentService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOfferAdvancePayment> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((offerAdvancePayment: HttpResponse<OfferAdvancePayment>) => {
          if (offerAdvancePayment.body) {
            return of(offerAdvancePayment.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OfferAdvancePayment());
  }
}

export const offerAdvancePaymentRoute: Routes = [
  {
    path: '',
    component: OfferAdvancePaymentComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.offerAdvancePayment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OfferAdvancePaymentDetailComponent,
    resolve: {
      offerAdvancePayment: OfferAdvancePaymentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.offerAdvancePayment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OfferAdvancePaymentUpdateComponent,
    resolve: {
      offerAdvancePayment: OfferAdvancePaymentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.offerAdvancePayment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OfferAdvancePaymentUpdateComponent,
    resolve: {
      offerAdvancePayment: OfferAdvancePaymentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.offerAdvancePayment.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
