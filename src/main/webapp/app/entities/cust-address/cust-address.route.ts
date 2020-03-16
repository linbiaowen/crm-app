import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICustAddress, CustAddress } from 'app/shared/model/cust-address.model';
import { CustAddressService } from './cust-address.service';
import { CustAddressComponent } from './cust-address.component';
import { CustAddressDetailComponent } from './cust-address-detail.component';
import { CustAddressUpdateComponent } from './cust-address-update.component';

@Injectable({ providedIn: 'root' })
export class CustAddressResolve implements Resolve<ICustAddress> {
  constructor(private service: CustAddressService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICustAddress> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((custAddress: HttpResponse<CustAddress>) => {
          if (custAddress.body) {
            return of(custAddress.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CustAddress());
  }
}

export const custAddressRoute: Routes = [
  {
    path: '',
    component: CustAddressComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.custAddress.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CustAddressDetailComponent,
    resolve: {
      custAddress: CustAddressResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.custAddress.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CustAddressUpdateComponent,
    resolve: {
      custAddress: CustAddressResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.custAddress.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CustAddressUpdateComponent,
    resolve: {
      custAddress: CustAddressResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.custAddress.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
