import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOrderProcessStatus, OrderProcessStatus } from 'app/shared/model/order-process-status.model';
import { OrderProcessStatusService } from './order-process-status.service';
import { OrderProcessStatusComponent } from './order-process-status.component';
import { OrderProcessStatusDetailComponent } from './order-process-status-detail.component';
import { OrderProcessStatusUpdateComponent } from './order-process-status-update.component';

@Injectable({ providedIn: 'root' })
export class OrderProcessStatusResolve implements Resolve<IOrderProcessStatus> {
  constructor(private service: OrderProcessStatusService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOrderProcessStatus> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((orderProcessStatus: HttpResponse<OrderProcessStatus>) => {
          if (orderProcessStatus.body) {
            return of(orderProcessStatus.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OrderProcessStatus());
  }
}

export const orderProcessStatusRoute: Routes = [
  {
    path: '',
    component: OrderProcessStatusComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.orderProcessStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OrderProcessStatusDetailComponent,
    resolve: {
      orderProcessStatus: OrderProcessStatusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.orderProcessStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OrderProcessStatusUpdateComponent,
    resolve: {
      orderProcessStatus: OrderProcessStatusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.orderProcessStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OrderProcessStatusUpdateComponent,
    resolve: {
      orderProcessStatus: OrderProcessStatusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.orderProcessStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
