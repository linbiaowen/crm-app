import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISubsOrderDetails, SubsOrderDetails } from 'app/shared/model/subs-order-details.model';
import { SubsOrderDetailsService } from './subs-order-details.service';
import { SubsOrderDetailsComponent } from './subs-order-details.component';
import { SubsOrderDetailsDetailComponent } from './subs-order-details-detail.component';
import { SubsOrderDetailsUpdateComponent } from './subs-order-details-update.component';

@Injectable({ providedIn: 'root' })
export class SubsOrderDetailsResolve implements Resolve<ISubsOrderDetails> {
  constructor(private service: SubsOrderDetailsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISubsOrderDetails> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((subsOrderDetails: HttpResponse<SubsOrderDetails>) => {
          if (subsOrderDetails.body) {
            return of(subsOrderDetails.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SubsOrderDetails());
  }
}

export const subsOrderDetailsRoute: Routes = [
  {
    path: '',
    component: SubsOrderDetailsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.subsOrderDetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SubsOrderDetailsDetailComponent,
    resolve: {
      subsOrderDetails: SubsOrderDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.subsOrderDetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SubsOrderDetailsUpdateComponent,
    resolve: {
      subsOrderDetails: SubsOrderDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.subsOrderDetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SubsOrderDetailsUpdateComponent,
    resolve: {
      subsOrderDetails: SubsOrderDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.subsOrderDetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
