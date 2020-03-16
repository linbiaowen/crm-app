import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBillCycle, BillCycle } from 'app/shared/model/bill-cycle.model';
import { BillCycleService } from './bill-cycle.service';
import { BillCycleComponent } from './bill-cycle.component';
import { BillCycleDetailComponent } from './bill-cycle-detail.component';
import { BillCycleUpdateComponent } from './bill-cycle-update.component';

@Injectable({ providedIn: 'root' })
export class BillCycleResolve implements Resolve<IBillCycle> {
  constructor(private service: BillCycleService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBillCycle> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((billCycle: HttpResponse<BillCycle>) => {
          if (billCycle.body) {
            return of(billCycle.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BillCycle());
  }
}

export const billCycleRoute: Routes = [
  {
    path: '',
    component: BillCycleComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.billCycle.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BillCycleDetailComponent,
    resolve: {
      billCycle: BillCycleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.billCycle.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BillCycleUpdateComponent,
    resolve: {
      billCycle: BillCycleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.billCycle.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BillCycleUpdateComponent,
    resolve: {
      billCycle: BillCycleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.billCycle.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
