import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBlackListMaster, BlackListMaster } from 'app/shared/model/black-list-master.model';
import { BlackListMasterService } from './black-list-master.service';
import { BlackListMasterComponent } from './black-list-master.component';
import { BlackListMasterDetailComponent } from './black-list-master-detail.component';
import { BlackListMasterUpdateComponent } from './black-list-master-update.component';

@Injectable({ providedIn: 'root' })
export class BlackListMasterResolve implements Resolve<IBlackListMaster> {
  constructor(private service: BlackListMasterService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBlackListMaster> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((blackListMaster: HttpResponse<BlackListMaster>) => {
          if (blackListMaster.body) {
            return of(blackListMaster.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BlackListMaster());
  }
}

export const blackListMasterRoute: Routes = [
  {
    path: '',
    component: BlackListMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.blackListMaster.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BlackListMasterDetailComponent,
    resolve: {
      blackListMaster: BlackListMasterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.blackListMaster.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BlackListMasterUpdateComponent,
    resolve: {
      blackListMaster: BlackListMasterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.blackListMaster.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BlackListMasterUpdateComponent,
    resolve: {
      blackListMaster: BlackListMasterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.blackListMaster.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
