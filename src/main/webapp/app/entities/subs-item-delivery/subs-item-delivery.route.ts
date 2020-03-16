import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISubsItemDelivery, SubsItemDelivery } from 'app/shared/model/subs-item-delivery.model';
import { SubsItemDeliveryService } from './subs-item-delivery.service';
import { SubsItemDeliveryComponent } from './subs-item-delivery.component';
import { SubsItemDeliveryDetailComponent } from './subs-item-delivery-detail.component';
import { SubsItemDeliveryUpdateComponent } from './subs-item-delivery-update.component';

@Injectable({ providedIn: 'root' })
export class SubsItemDeliveryResolve implements Resolve<ISubsItemDelivery> {
  constructor(private service: SubsItemDeliveryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISubsItemDelivery> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((subsItemDelivery: HttpResponse<SubsItemDelivery>) => {
          if (subsItemDelivery.body) {
            return of(subsItemDelivery.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SubsItemDelivery());
  }
}

export const subsItemDeliveryRoute: Routes = [
  {
    path: '',
    component: SubsItemDeliveryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.subsItemDelivery.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SubsItemDeliveryDetailComponent,
    resolve: {
      subsItemDelivery: SubsItemDeliveryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.subsItemDelivery.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SubsItemDeliveryUpdateComponent,
    resolve: {
      subsItemDelivery: SubsItemDeliveryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.subsItemDelivery.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SubsItemDeliveryUpdateComponent,
    resolve: {
      subsItemDelivery: SubsItemDeliveryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.subsItemDelivery.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
