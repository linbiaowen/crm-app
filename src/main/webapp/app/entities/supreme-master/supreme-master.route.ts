import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISupremeMaster, SupremeMaster } from 'app/shared/model/supreme-master.model';
import { SupremeMasterService } from './supreme-master.service';
import { SupremeMasterComponent } from './supreme-master.component';
import { SupremeMasterDetailComponent } from './supreme-master-detail.component';
import { SupremeMasterUpdateComponent } from './supreme-master-update.component';

@Injectable({ providedIn: 'root' })
export class SupremeMasterResolve implements Resolve<ISupremeMaster> {
  constructor(private service: SupremeMasterService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISupremeMaster> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((supremeMaster: HttpResponse<SupremeMaster>) => {
          if (supremeMaster.body) {
            return of(supremeMaster.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SupremeMaster());
  }
}

export const supremeMasterRoute: Routes = [
  {
    path: '',
    component: SupremeMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.supremeMaster.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SupremeMasterDetailComponent,
    resolve: {
      supremeMaster: SupremeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.supremeMaster.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SupremeMasterUpdateComponent,
    resolve: {
      supremeMaster: SupremeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.supremeMaster.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SupremeMasterUpdateComponent,
    resolve: {
      supremeMaster: SupremeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.supremeMaster.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
