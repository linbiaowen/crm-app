import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISubsDeliveryItemDetails, SubsDeliveryItemDetails } from 'app/shared/model/subs-delivery-item-details.model';
import { SubsDeliveryItemDetailsService } from './subs-delivery-item-details.service';
import { SubsDeliveryItemDetailsComponent } from './subs-delivery-item-details.component';
import { SubsDeliveryItemDetailsDetailComponent } from './subs-delivery-item-details-detail.component';
import { SubsDeliveryItemDetailsUpdateComponent } from './subs-delivery-item-details-update.component';

@Injectable({ providedIn: 'root' })
export class SubsDeliveryItemDetailsResolve implements Resolve<ISubsDeliveryItemDetails> {
  constructor(private service: SubsDeliveryItemDetailsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISubsDeliveryItemDetails> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((subsDeliveryItemDetails: HttpResponse<SubsDeliveryItemDetails>) => {
          if (subsDeliveryItemDetails.body) {
            return of(subsDeliveryItemDetails.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SubsDeliveryItemDetails());
  }
}

export const subsDeliveryItemDetailsRoute: Routes = [
  {
    path: '',
    component: SubsDeliveryItemDetailsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.subsDeliveryItemDetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SubsDeliveryItemDetailsDetailComponent,
    resolve: {
      subsDeliveryItemDetails: SubsDeliveryItemDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.subsDeliveryItemDetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SubsDeliveryItemDetailsUpdateComponent,
    resolve: {
      subsDeliveryItemDetails: SubsDeliveryItemDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.subsDeliveryItemDetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SubsDeliveryItemDetailsUpdateComponent,
    resolve: {
      subsDeliveryItemDetails: SubsDeliveryItemDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.subsDeliveryItemDetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
