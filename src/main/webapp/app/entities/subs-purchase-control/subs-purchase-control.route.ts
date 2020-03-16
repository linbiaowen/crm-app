import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISubsPurchaseControl, SubsPurchaseControl } from 'app/shared/model/subs-purchase-control.model';
import { SubsPurchaseControlService } from './subs-purchase-control.service';
import { SubsPurchaseControlComponent } from './subs-purchase-control.component';
import { SubsPurchaseControlDetailComponent } from './subs-purchase-control-detail.component';
import { SubsPurchaseControlUpdateComponent } from './subs-purchase-control-update.component';

@Injectable({ providedIn: 'root' })
export class SubsPurchaseControlResolve implements Resolve<ISubsPurchaseControl> {
  constructor(private service: SubsPurchaseControlService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISubsPurchaseControl> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((subsPurchaseControl: HttpResponse<SubsPurchaseControl>) => {
          if (subsPurchaseControl.body) {
            return of(subsPurchaseControl.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SubsPurchaseControl());
  }
}

export const subsPurchaseControlRoute: Routes = [
  {
    path: '',
    component: SubsPurchaseControlComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.subsPurchaseControl.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SubsPurchaseControlDetailComponent,
    resolve: {
      subsPurchaseControl: SubsPurchaseControlResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.subsPurchaseControl.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SubsPurchaseControlUpdateComponent,
    resolve: {
      subsPurchaseControl: SubsPurchaseControlResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.subsPurchaseControl.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SubsPurchaseControlUpdateComponent,
    resolve: {
      subsPurchaseControl: SubsPurchaseControlResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.subsPurchaseControl.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
