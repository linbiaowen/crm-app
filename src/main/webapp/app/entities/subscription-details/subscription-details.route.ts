import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISubscriptionDetails, SubscriptionDetails } from 'app/shared/model/subscription-details.model';
import { SubscriptionDetailsService } from './subscription-details.service';
import { SubscriptionDetailsComponent } from './subscription-details.component';
import { SubscriptionDetailsDetailComponent } from './subscription-details-detail.component';
import { SubscriptionDetailsUpdateComponent } from './subscription-details-update.component';

@Injectable({ providedIn: 'root' })
export class SubscriptionDetailsResolve implements Resolve<ISubscriptionDetails> {
  constructor(private service: SubscriptionDetailsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISubscriptionDetails> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((subscriptionDetails: HttpResponse<SubscriptionDetails>) => {
          if (subscriptionDetails.body) {
            return of(subscriptionDetails.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SubscriptionDetails());
  }
}

export const subscriptionDetailsRoute: Routes = [
  {
    path: '',
    component: SubscriptionDetailsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.subscriptionDetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SubscriptionDetailsDetailComponent,
    resolve: {
      subscriptionDetails: SubscriptionDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.subscriptionDetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SubscriptionDetailsUpdateComponent,
    resolve: {
      subscriptionDetails: SubscriptionDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.subscriptionDetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SubscriptionDetailsUpdateComponent,
    resolve: {
      subscriptionDetails: SubscriptionDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.subscriptionDetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
