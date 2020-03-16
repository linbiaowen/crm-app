import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOrderProcessStatusHistory, OrderProcessStatusHistory } from 'app/shared/model/order-process-status-history.model';
import { OrderProcessStatusHistoryService } from './order-process-status-history.service';
import { OrderProcessStatusHistoryComponent } from './order-process-status-history.component';
import { OrderProcessStatusHistoryDetailComponent } from './order-process-status-history-detail.component';
import { OrderProcessStatusHistoryUpdateComponent } from './order-process-status-history-update.component';

@Injectable({ providedIn: 'root' })
export class OrderProcessStatusHistoryResolve implements Resolve<IOrderProcessStatusHistory> {
  constructor(private service: OrderProcessStatusHistoryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOrderProcessStatusHistory> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((orderProcessStatusHistory: HttpResponse<OrderProcessStatusHistory>) => {
          if (orderProcessStatusHistory.body) {
            return of(orderProcessStatusHistory.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OrderProcessStatusHistory());
  }
}

export const orderProcessStatusHistoryRoute: Routes = [
  {
    path: '',
    component: OrderProcessStatusHistoryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.orderProcessStatusHistory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OrderProcessStatusHistoryDetailComponent,
    resolve: {
      orderProcessStatusHistory: OrderProcessStatusHistoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.orderProcessStatusHistory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OrderProcessStatusHistoryUpdateComponent,
    resolve: {
      orderProcessStatusHistory: OrderProcessStatusHistoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.orderProcessStatusHistory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OrderProcessStatusHistoryUpdateComponent,
    resolve: {
      orderProcessStatusHistory: OrderProcessStatusHistoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.orderProcessStatusHistory.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
