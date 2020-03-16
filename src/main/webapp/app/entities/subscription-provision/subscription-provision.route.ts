import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISubscriptionProvision, SubscriptionProvision } from 'app/shared/model/subscription-provision.model';
import { SubscriptionProvisionService } from './subscription-provision.service';
import { SubscriptionProvisionComponent } from './subscription-provision.component';
import { SubscriptionProvisionDetailComponent } from './subscription-provision-detail.component';
import { SubscriptionProvisionUpdateComponent } from './subscription-provision-update.component';

@Injectable({ providedIn: 'root' })
export class SubscriptionProvisionResolve implements Resolve<ISubscriptionProvision> {
  constructor(private service: SubscriptionProvisionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISubscriptionProvision> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((subscriptionProvision: HttpResponse<SubscriptionProvision>) => {
          if (subscriptionProvision.body) {
            return of(subscriptionProvision.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SubscriptionProvision());
  }
}

export const subscriptionProvisionRoute: Routes = [
  {
    path: '',
    component: SubscriptionProvisionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.subscriptionProvision.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SubscriptionProvisionDetailComponent,
    resolve: {
      subscriptionProvision: SubscriptionProvisionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.subscriptionProvision.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SubscriptionProvisionUpdateComponent,
    resolve: {
      subscriptionProvision: SubscriptionProvisionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.subscriptionProvision.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SubscriptionProvisionUpdateComponent,
    resolve: {
      subscriptionProvision: SubscriptionProvisionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.subscriptionProvision.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
