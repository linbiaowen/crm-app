import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOrderMaster, OrderMaster } from 'app/shared/model/order-master.model';
import { OrderMasterService } from './order-master.service';
import { OrderMasterComponent } from './order-master.component';
import { OrderMasterDetailComponent } from './order-master-detail.component';
import { OrderMasterUpdateComponent } from './order-master-update.component';

@Injectable({ providedIn: 'root' })
export class OrderMasterResolve implements Resolve<IOrderMaster> {
  constructor(private service: OrderMasterService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOrderMaster> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((orderMaster: HttpResponse<OrderMaster>) => {
          if (orderMaster.body) {
            return of(orderMaster.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OrderMaster());
  }
}

export const orderMasterRoute: Routes = [
  {
    path: '',
    component: OrderMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.orderMaster.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OrderMasterDetailComponent,
    resolve: {
      orderMaster: OrderMasterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.orderMaster.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OrderMasterUpdateComponent,
    resolve: {
      orderMaster: OrderMasterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.orderMaster.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OrderMasterUpdateComponent,
    resolve: {
      orderMaster: OrderMasterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.orderMaster.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
