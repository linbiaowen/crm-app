import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICustDocDataStore, CustDocDataStore } from 'app/shared/model/cust-doc-data-store.model';
import { CustDocDataStoreService } from './cust-doc-data-store.service';
import { CustDocDataStoreComponent } from './cust-doc-data-store.component';
import { CustDocDataStoreDetailComponent } from './cust-doc-data-store-detail.component';
import { CustDocDataStoreUpdateComponent } from './cust-doc-data-store-update.component';

@Injectable({ providedIn: 'root' })
export class CustDocDataStoreResolve implements Resolve<ICustDocDataStore> {
  constructor(private service: CustDocDataStoreService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICustDocDataStore> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((custDocDataStore: HttpResponse<CustDocDataStore>) => {
          if (custDocDataStore.body) {
            return of(custDocDataStore.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CustDocDataStore());
  }
}

export const custDocDataStoreRoute: Routes = [
  {
    path: '',
    component: CustDocDataStoreComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.custDocDataStore.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CustDocDataStoreDetailComponent,
    resolve: {
      custDocDataStore: CustDocDataStoreResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.custDocDataStore.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CustDocDataStoreUpdateComponent,
    resolve: {
      custDocDataStore: CustDocDataStoreResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.custDocDataStore.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CustDocDataStoreUpdateComponent,
    resolve: {
      custDocDataStore: CustDocDataStoreResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.custDocDataStore.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
