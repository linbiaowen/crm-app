import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICustSubscription, CustSubscription } from 'app/shared/model/cust-subscription.model';
import { CustSubscriptionService } from './cust-subscription.service';
import { CustSubscriptionComponent } from './cust-subscription.component';
import { CustSubscriptionDetailComponent } from './cust-subscription-detail.component';
import { CustSubscriptionUpdateComponent } from './cust-subscription-update.component';

@Injectable({ providedIn: 'root' })
export class CustSubscriptionResolve implements Resolve<ICustSubscription> {
  constructor(private service: CustSubscriptionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICustSubscription> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((custSubscription: HttpResponse<CustSubscription>) => {
          if (custSubscription.body) {
            return of(custSubscription.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CustSubscription());
  }
}

export const custSubscriptionRoute: Routes = [
  {
    path: '',
    component: CustSubscriptionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.custSubscription.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CustSubscriptionDetailComponent,
    resolve: {
      custSubscription: CustSubscriptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.custSubscription.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CustSubscriptionUpdateComponent,
    resolve: {
      custSubscription: CustSubscriptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.custSubscription.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CustSubscriptionUpdateComponent,
    resolve: {
      custSubscription: CustSubscriptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.custSubscription.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
