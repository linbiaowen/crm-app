import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICustAcctBlackList, CustAcctBlackList } from 'app/shared/model/cust-acct-black-list.model';
import { CustAcctBlackListService } from './cust-acct-black-list.service';
import { CustAcctBlackListComponent } from './cust-acct-black-list.component';
import { CustAcctBlackListDetailComponent } from './cust-acct-black-list-detail.component';
import { CustAcctBlackListUpdateComponent } from './cust-acct-black-list-update.component';

@Injectable({ providedIn: 'root' })
export class CustAcctBlackListResolve implements Resolve<ICustAcctBlackList> {
  constructor(private service: CustAcctBlackListService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICustAcctBlackList> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((custAcctBlackList: HttpResponse<CustAcctBlackList>) => {
          if (custAcctBlackList.body) {
            return of(custAcctBlackList.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CustAcctBlackList());
  }
}

export const custAcctBlackListRoute: Routes = [
  {
    path: '',
    component: CustAcctBlackListComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.custAcctBlackList.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CustAcctBlackListDetailComponent,
    resolve: {
      custAcctBlackList: CustAcctBlackListResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.custAcctBlackList.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CustAcctBlackListUpdateComponent,
    resolve: {
      custAcctBlackList: CustAcctBlackListResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.custAcctBlackList.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CustAcctBlackListUpdateComponent,
    resolve: {
      custAcctBlackList: CustAcctBlackListResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.custAcctBlackList.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
