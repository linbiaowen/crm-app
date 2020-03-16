import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOrderProcessConfig, OrderProcessConfig } from 'app/shared/model/order-process-config.model';
import { OrderProcessConfigService } from './order-process-config.service';
import { OrderProcessConfigComponent } from './order-process-config.component';
import { OrderProcessConfigDetailComponent } from './order-process-config-detail.component';
import { OrderProcessConfigUpdateComponent } from './order-process-config-update.component';

@Injectable({ providedIn: 'root' })
export class OrderProcessConfigResolve implements Resolve<IOrderProcessConfig> {
  constructor(private service: OrderProcessConfigService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOrderProcessConfig> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((orderProcessConfig: HttpResponse<OrderProcessConfig>) => {
          if (orderProcessConfig.body) {
            return of(orderProcessConfig.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OrderProcessConfig());
  }
}

export const orderProcessConfigRoute: Routes = [
  {
    path: '',
    component: OrderProcessConfigComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.orderProcessConfig.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OrderProcessConfigDetailComponent,
    resolve: {
      orderProcessConfig: OrderProcessConfigResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.orderProcessConfig.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OrderProcessConfigUpdateComponent,
    resolve: {
      orderProcessConfig: OrderProcessConfigResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.orderProcessConfig.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OrderProcessConfigUpdateComponent,
    resolve: {
      orderProcessConfig: OrderProcessConfigResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.orderProcessConfig.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
