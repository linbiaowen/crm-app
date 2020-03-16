import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISalesChannelMaster, SalesChannelMaster } from 'app/shared/model/sales-channel-master.model';
import { SalesChannelMasterService } from './sales-channel-master.service';
import { SalesChannelMasterComponent } from './sales-channel-master.component';
import { SalesChannelMasterDetailComponent } from './sales-channel-master-detail.component';
import { SalesChannelMasterUpdateComponent } from './sales-channel-master-update.component';

@Injectable({ providedIn: 'root' })
export class SalesChannelMasterResolve implements Resolve<ISalesChannelMaster> {
  constructor(private service: SalesChannelMasterService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISalesChannelMaster> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((salesChannelMaster: HttpResponse<SalesChannelMaster>) => {
          if (salesChannelMaster.body) {
            return of(salesChannelMaster.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SalesChannelMaster());
  }
}

export const salesChannelMasterRoute: Routes = [
  {
    path: '',
    component: SalesChannelMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.salesChannelMaster.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SalesChannelMasterDetailComponent,
    resolve: {
      salesChannelMaster: SalesChannelMasterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.salesChannelMaster.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SalesChannelMasterUpdateComponent,
    resolve: {
      salesChannelMaster: SalesChannelMasterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.salesChannelMaster.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SalesChannelMasterUpdateComponent,
    resolve: {
      salesChannelMaster: SalesChannelMasterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.salesChannelMaster.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
