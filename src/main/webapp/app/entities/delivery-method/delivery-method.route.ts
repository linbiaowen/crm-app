import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDeliveryMethod, DeliveryMethod } from 'app/shared/model/delivery-method.model';
import { DeliveryMethodService } from './delivery-method.service';
import { DeliveryMethodComponent } from './delivery-method.component';
import { DeliveryMethodDetailComponent } from './delivery-method-detail.component';
import { DeliveryMethodUpdateComponent } from './delivery-method-update.component';

@Injectable({ providedIn: 'root' })
export class DeliveryMethodResolve implements Resolve<IDeliveryMethod> {
  constructor(private service: DeliveryMethodService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDeliveryMethod> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((deliveryMethod: HttpResponse<DeliveryMethod>) => {
          if (deliveryMethod.body) {
            return of(deliveryMethod.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DeliveryMethod());
  }
}

export const deliveryMethodRoute: Routes = [
  {
    path: '',
    component: DeliveryMethodComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.deliveryMethod.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DeliveryMethodDetailComponent,
    resolve: {
      deliveryMethod: DeliveryMethodResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.deliveryMethod.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DeliveryMethodUpdateComponent,
    resolve: {
      deliveryMethod: DeliveryMethodResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.deliveryMethod.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DeliveryMethodUpdateComponent,
    resolve: {
      deliveryMethod: DeliveryMethodResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.deliveryMethod.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
