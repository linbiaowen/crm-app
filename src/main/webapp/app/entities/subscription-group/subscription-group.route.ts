import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISubscriptionGroup, SubscriptionGroup } from 'app/shared/model/subscription-group.model';
import { SubscriptionGroupService } from './subscription-group.service';
import { SubscriptionGroupComponent } from './subscription-group.component';
import { SubscriptionGroupDetailComponent } from './subscription-group-detail.component';
import { SubscriptionGroupUpdateComponent } from './subscription-group-update.component';

@Injectable({ providedIn: 'root' })
export class SubscriptionGroupResolve implements Resolve<ISubscriptionGroup> {
  constructor(private service: SubscriptionGroupService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISubscriptionGroup> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((subscriptionGroup: HttpResponse<SubscriptionGroup>) => {
          if (subscriptionGroup.body) {
            return of(subscriptionGroup.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SubscriptionGroup());
  }
}

export const subscriptionGroupRoute: Routes = [
  {
    path: '',
    component: SubscriptionGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.subscriptionGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SubscriptionGroupDetailComponent,
    resolve: {
      subscriptionGroup: SubscriptionGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.subscriptionGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SubscriptionGroupUpdateComponent,
    resolve: {
      subscriptionGroup: SubscriptionGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.subscriptionGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SubscriptionGroupUpdateComponent,
    resolve: {
      subscriptionGroup: SubscriptionGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.subscriptionGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
