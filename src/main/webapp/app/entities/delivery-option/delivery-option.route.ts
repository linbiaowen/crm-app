import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDeliveryOption, DeliveryOption } from 'app/shared/model/delivery-option.model';
import { DeliveryOptionService } from './delivery-option.service';
import { DeliveryOptionComponent } from './delivery-option.component';
import { DeliveryOptionDetailComponent } from './delivery-option-detail.component';
import { DeliveryOptionUpdateComponent } from './delivery-option-update.component';

@Injectable({ providedIn: 'root' })
export class DeliveryOptionResolve implements Resolve<IDeliveryOption> {
  constructor(private service: DeliveryOptionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDeliveryOption> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((deliveryOption: HttpResponse<DeliveryOption>) => {
          if (deliveryOption.body) {
            return of(deliveryOption.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DeliveryOption());
  }
}

export const deliveryOptionRoute: Routes = [
  {
    path: '',
    component: DeliveryOptionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.deliveryOption.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DeliveryOptionDetailComponent,
    resolve: {
      deliveryOption: DeliveryOptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.deliveryOption.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DeliveryOptionUpdateComponent,
    resolve: {
      deliveryOption: DeliveryOptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.deliveryOption.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DeliveryOptionUpdateComponent,
    resolve: {
      deliveryOption: DeliveryOptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.deliveryOption.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
