import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICustCommOptoutMaster, CustCommOptoutMaster } from 'app/shared/model/cust-comm-optout-master.model';
import { CustCommOptoutMasterService } from './cust-comm-optout-master.service';
import { CustCommOptoutMasterComponent } from './cust-comm-optout-master.component';
import { CustCommOptoutMasterDetailComponent } from './cust-comm-optout-master-detail.component';
import { CustCommOptoutMasterUpdateComponent } from './cust-comm-optout-master-update.component';

@Injectable({ providedIn: 'root' })
export class CustCommOptoutMasterResolve implements Resolve<ICustCommOptoutMaster> {
  constructor(private service: CustCommOptoutMasterService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICustCommOptoutMaster> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((custCommOptoutMaster: HttpResponse<CustCommOptoutMaster>) => {
          if (custCommOptoutMaster.body) {
            return of(custCommOptoutMaster.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CustCommOptoutMaster());
  }
}

export const custCommOptoutMasterRoute: Routes = [
  {
    path: '',
    component: CustCommOptoutMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.custCommOptoutMaster.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CustCommOptoutMasterDetailComponent,
    resolve: {
      custCommOptoutMaster: CustCommOptoutMasterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.custCommOptoutMaster.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CustCommOptoutMasterUpdateComponent,
    resolve: {
      custCommOptoutMaster: CustCommOptoutMasterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.custCommOptoutMaster.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CustCommOptoutMasterUpdateComponent,
    resolve: {
      custCommOptoutMaster: CustCommOptoutMasterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.custCommOptoutMaster.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
